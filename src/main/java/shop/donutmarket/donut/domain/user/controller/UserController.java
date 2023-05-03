package shop.donutmarket.donut.domain.user.controller;

import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import shop.donutmarket.donut.domain.user.dto.UserReq;
import shop.donutmarket.donut.domain.user.dto.UserResp;
import shop.donutmarket.donut.domain.user.model.User;
import shop.donutmarket.donut.domain.user.repository.UserRepository;
import shop.donutmarket.donut.domain.user.service.UserService;
import shop.donutmarket.donut.global.auth.MyUserDetails;
import shop.donutmarket.donut.global.exception.Exception404;
import shop.donutmarket.donut.global.exception.Exception500;
import shop.donutmarket.donut.global.jwt.MyJwtProvider;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController {
    
    private final UserService userService;
    private final UserRepository userRepository;

    @Value("${meta.name}")
    private String name;

    @PostMapping("/join")
    public ResponseEntity<?> join(@RequestBody @Valid UserReq.JoinDTO joinDTO, BindingResult bindingResult) {
        ResponseEntity responseEntity = userService.회원가입(joinDTO);
        return responseEntity;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid UserReq.LoginDTO loginDTO, BindingResult bindingResult) {
        ResponseEntity responseEntity = userService.로그인(loginDTO);
        return responseEntity;
    }

    @PutMapping("/users/update")
    public ResponseEntity<?> update(@AuthenticationPrincipal MyUserDetails myUserDetails, BindingResult bindingResult, @RequestBody @Valid UserReq.UpdateDTO updateDTO) {
        UserResp.UpdateDTO resp = userService.회원수정(myUserDetails, updateDTO);
        return ResponseEntity.ok(resp);
    }

    @GetMapping("/jwtToken")
    public ResponseEntity<?> jwtToken(HttpServletRequest request) {
        String jwtToken = request.getHeader("Authorization");
        if (jwtToken == null) {
            throw new Exception404("토큰이 헤더에 없습니다");
        }
        System.out.println("토큰이 헤더에 있습니다");
        jwtToken = jwtToken.replace(MyJwtProvider.TOKEN_PREFIX, "");
        DecodedJWT decodedJWT = MyJwtProvider.verify(jwtToken);
        Long userId = decodedJWT.getClaim("id").asLong();
        User userEntity = userRepository.findByIdJoinFetch(userId).orElseThrow(() -> new Exception500("토큰 검증 실패"));
        return ResponseEntity.ok(userEntity);
    }
}
