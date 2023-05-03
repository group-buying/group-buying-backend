package shop.donutmarket.donut.domain.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import shop.donutmarket.donut.domain.review.model.Rate;
import shop.donutmarket.donut.domain.review.repository.RateRepository;
import shop.donutmarket.donut.domain.user.dto.UserResp;
import shop.donutmarket.donut.domain.user.model.User;
import shop.donutmarket.donut.domain.user.repository.UserRepository;
import shop.donutmarket.donut.global.dto.ResponseDTO;
import shop.donutmarket.donut.global.exception.Exception404;
import shop.donutmarket.donut.global.exception.Exception500;
import shop.donutmarket.donut.global.jwt.MyJwtProvider;
import shop.donutmarket.donut.global.oauth.NaverUserInfo;

import java.util.Map;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class OauthController {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final RateRepository rateRepository;

    private static final String NAVER_PROFILE_API_URL = "https://openapi.naver.com/v1/nid/me";
    private static final String SECRET = System.getenv("HS512_SECRET");

    @PostMapping("/oauth/naver")
    public ResponseEntity<?> jwtCreate(@RequestHeader("Authorization") String accessToken) {

        // Header에 토큰 담기
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer "+accessToken);

        // Profile 받기 위해 요청
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Map<String, Object>> response = restTemplate.
                exchange(NAVER_PROFILE_API_URL, HttpMethod.GET,
                        new HttpEntity<>(headers), new ParameterizedTypeReference<>() {
                        });

        Map<String, Object> data = response.getBody();

        // response 데이터 NaverUserInfo로 매핑
        NaverUserInfo naverUser =
                new NaverUserInfo((Map<String, Object>) data.get("response"));

        // username 조회
        Optional<User> userOP =
                userRepository.findByUsername(naverUser.getProvider() + "_" + naverUser.getProviderId());

        // 회원가입 안됐을 시
        if (userOP.isEmpty()) {
            Optional<Rate> rateOP = rateRepository.findById(1L);
            if (rateOP.isEmpty()) {
                throw new Exception404("등급이 존재하지 않습니다");
            }

            Rate ratePS = rateOP.get();

            User user = User.builder()
                    .username(naverUser.getProvider() + "_" + naverUser.getProviderId())
                    .password(passwordEncoder.encode(SECRET))
                    .email(naverUser.getEmail())
                    .provider(naverUser.getProvider())
                    .providerId(naverUser.getProviderId())
                    .rate(ratePS)
                    .role("ROLE_USER")
                    .build();

            try {
                User userEntity = userRepository.save(user);
                String jwt = MyJwtProvider.create(userEntity);

                // Body 만들기
                UserResp.JoinDTO body = new UserResp.JoinDTO(userEntity);
                ResponseDTO<?> responseDTO = new ResponseDTO<>(body);

                // ResponseEntity 생성
                return ResponseEntity.ok().header(MyJwtProvider.HEADER, jwt).body(responseDTO);

            } catch (Exception e) {
                throw new Exception500("네이버 로그인 실패 : " + e.getMessage());
            }

        }

        // 회원가입 되있을 시
        else {
            try {
                User userPS = userOP.get();

                // user가 존재하면 update 해주기
                userPS.updateEmail(naverUser.getEmail());
                User userEntity = userRepository.save(userPS);
                String jwt = MyJwtProvider.create(userPS);

                // Body 만들기
                UserResp.LoginDTO body = new UserResp.LoginDTO(userEntity);
                ResponseDTO<?> responseDTO = new ResponseDTO<>(body);

                // ResponseEntity 생성
                return ResponseEntity.ok().header(MyJwtProvider.HEADER, jwt).body(responseDTO);
            } catch (Exception e) {
                throw new Exception500("네이버 로그인 실패 : " + e.getMessage());
            }
        }
    }
}
