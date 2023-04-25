package shop.donutmarket.donut.domain.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import shop.donutmarket.donut.domain.user.model.User;
import shop.donutmarket.donut.domain.user.repository.UserRepository;
import shop.donutmarket.donut.global.jwt.MyJwtProvider;
import shop.donutmarket.donut.global.oauth.NaverUserInfo;

import java.util.Map;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class OauthController {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private static final String SECRET = System.getenv("HS512_SECRET");

    @PostMapping("/oauth/naver")
    public String jwtCreate(@RequestBody Map<String, Object> data) {

        NaverUserInfo naverUser =
                new NaverUserInfo((Map<String, Object>) data.get("response"));

        Optional<User> userOP =
                userRepository.findByUsername(naverUser.getProvider() + "_" + naverUser.getProviderId());

        if (userOP.isEmpty()) {
            User user = User.builder()
                    .username(naverUser.getProvider() + "_" + naverUser.getProviderId())
                    .password(passwordEncoder.encode(SECRET))
                    .email(naverUser.getEmail())
                    .provider(naverUser.getProvider())
                    .providerId(naverUser.getProviderId())
                    .role("ROLE_USER")
                    .build();

            User userEntity = userRepository.save(user);
            String jwt = MyJwtProvider.create(userEntity);

            return  jwt;

        } else {
            User userPS = userOP.get();
            // user가 존재하면 update 해주기
            userPS.updateEmail(naverUser.getEmail());
            userRepository.save(userPS);
            String jwt = MyJwtProvider.create(userPS);

            return jwt;
        }
    }
}
