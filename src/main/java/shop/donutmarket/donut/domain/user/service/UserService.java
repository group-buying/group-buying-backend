package shop.donutmarket.donut.domain.user.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import shop.donutmarket.donut.domain.user.dto.UserReq;
import shop.donutmarket.donut.domain.user.dto.UserResp;
import shop.donutmarket.donut.domain.user.dto.UserResp.AdminSearchUserDTO;
import shop.donutmarket.donut.domain.user.model.User;
import shop.donutmarket.donut.domain.user.repository.UserRepository;
import shop.donutmarket.donut.global.auth.MyUserDetails;
import shop.donutmarket.donut.global.exception.Exception404;
import shop.donutmarket.donut.global.exception.Exception500;
import shop.donutmarket.donut.global.jwt.MyJwtProvider;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Transactional
    public String 회원가입(UserReq.JoinDTO joinDTO) {
        // 회원가입 로직
        String rawPassword = joinDTO.getPassword();
        String encPassword = passwordEncoder.encode(rawPassword); // 60Byte
        joinDTO.setPassword(encPassword);

        try {
            userRepository.save(joinDTO.toEntity());
        } catch (Exception e) {
            throw new Exception500("회원가입 실패 : " + e.getMessage());
        }

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
        Optional<User> userOP = userRepository.findByUsername(loginDTO.getUsername());
        if (userOP.isEmpty()) {
            throw new Exception404("존재하지 않는 회원입니다");
        }
        try {
            User userPS = userOP.get();
            if (passwordEncoder.matches(loginDTO.getPassword(), userPS.getPassword())) {
                String jwt = MyJwtProvider.create(userPS);
                return jwt;
            }
            throw new RuntimeException("패스워드 유효성 실패");
        } catch (Exception e) {
            throw new Exception500("로그인 실패 : " + e.getMessage());
        }
    }

    @Transactional
    public UserResp.UpdateDTO 회원수정(@AuthenticationPrincipal MyUserDetails myUserDetails, UserReq.UpdateDTO updateDTO) {
        Optional<User> userOP = userRepository.findById(myUserDetails.getUser().getId());

        if (userOP.isEmpty()) {
            throw new Exception404("존재하지 않는 회원입니다");
        }

        try {
            // 회원 수정
            User userPS = userOP.get();
            LocalDateTime localDateTime = LocalDateTime.now();
            userPS.updateUser(updateDTO.getPassword(), updateDTO.getName(), updateDTO.getProfile(), localDateTime);

        } catch (Exception e) {
            throw new Exception500("회원수정 실패 : " + e.getMessage());
        }

        // 다시 db에서 조회
        Optional<User> data = userRepository.findById(myUserDetails.getUser().getId());

        if (data.isEmpty()) {
            throw new Exception404("존재하지 않는 회원입니다");
        }

        try {
            User user = data.get();
            UserResp.UpdateDTO resp = new UserResp.UpdateDTO(user.getUsername(), user.getEmail(), user.getName(), user.getProfile(), user.getRole());
            return resp;
        } catch (Exception e) {
            throw new Exception500("회원수정 데이터 반환 실패 : " + e.getMessage());
        }
    }

    @Transactional(readOnly = true)
    public List<AdminSearchUserDTO> 관리자유저조회() {
        List<User> userlist = userRepository.findAllWithAllArg();
        List<AdminSearchUserDTO> listDTO = new ArrayList<>();
        try {
            for (User user : userlist) {
                AdminSearchUserDTO userDTO = new AdminSearchUserDTO(
                    user.getId(), user.getName(), user.getRate().getRateName(),
                    user.getStatusCode().getStatus(), user.getCreatedAt());
                listDTO.add(userDTO); 
            }
            return listDTO;
        } catch (Exception e) {
            throw new Exception500("조회에 실패하였습니다.");
        }
    }

    @Transactional
    public void 관리자유저차단(Long id) {
        Optional<User> userOP = userRepository.findById(id);
        if (userOP.isPresent()) {
            throw new Exception404("존재하지 않는 유저입니다.");
        }
        try {
            User userPS = userOP.get();
            userPS.blockUser();
        } catch (Exception e) {
            throw new Exception500("차단에 실패하였습니다.");
        }
    }
    
}
