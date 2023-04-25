package shop.donutmarket.donut.domain.user.service;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import shop.donutmarket.donut.domain.user.dto.UserReq;
import shop.donutmarket.donut.domain.user.model.User;
import shop.donutmarket.donut.domain.user.repository.UserRepository;
import shop.donutmarket.donut.global.auth.MyUserDetails;
import shop.donutmarket.donut.global.jwt.MyJwtProvider;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    /*
     * 1. 트랜젝션 관리
     * 2. 영속성 객체 변경감지
     * 3. RequestDTO 요청받기
     * 4. 비즈니스 로직 처리하기
     * 5. ResponseDTO 응답하기
     */

    @Transactional
    public String 회원가입(UserReq.JoinDTO joinDTO) {
        // 회원가입 로직
        String rawPassword = joinDTO.getPassword();
        String encPassword = passwordEncoder.encode(rawPassword); // 60Byte
        joinDTO.setPassword(encPassword);
        userRepository.save(joinDTO.toEntity());

        // JWT 인증 로직
        Optional<User> userOP = userRepository.findByUsername(joinDTO.getEmail());
        if (userOP.isPresent()) {
            User userPS = userOP.get(); // 조회하는 객체는 PS
            if (passwordEncoder.matches(rawPassword, userPS.getPassword())) {
                String jwt = MyJwtProvider.create(userPS);
                return jwt;
            }
            throw new RuntimeException("패스워드 유효성 실패");
        } else {
            throw new RuntimeException("이메일 유효성 실패");
        }
    }

    @Transactional(readOnly = true)
    public String 로그인(UserReq.LoginDTO loginDTO) {
        Optional<User> userOP = userRepository.findByUsername(loginDTO.getEmail());
        if (userOP.isPresent()) {
            User userPS = userOP.get();
            if (passwordEncoder.matches(loginDTO.getPassword(), userPS.getPassword())) {
                String jwt = MyJwtProvider.create(userPS);
                return jwt;
            }
            throw new RuntimeException("패스워드 유효성 실패");
        } else {
            throw new RuntimeException("이메일 유효성 실패");
        }
    }

    @Transactional
    public Optional<User> 회원수정(@AuthenticationPrincipal MyUserDetails myUserDetails, UserReq.UpdateDTO updateDTO) {
        Optional<User> userOP = userRepository.findById(myUserDetails.getUser().getId());
        if (userOP.isPresent()) {
            User userPS = userOP.get();
            LocalDateTime localDateTime = LocalDateTime.now();
            userPS.updateUser(updateDTO.getPassword(), updateDTO.getName(), updateDTO.getProfile(), localDateTime);
        }
        Optional<User> data = userRepository.findById(myUserDetails.getUser().getId());
        return data;
    }
}
