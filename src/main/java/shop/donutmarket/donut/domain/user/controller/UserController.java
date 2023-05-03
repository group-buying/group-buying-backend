package shop.donutmarket.donut.domain.user.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import shop.donutmarket.donut.domain.user.dto.UserReq;
import shop.donutmarket.donut.domain.user.dto.UserResp;
import shop.donutmarket.donut.domain.user.service.UserService;
import shop.donutmarket.donut.global.auth.MyUserDetails;
import shop.donutmarket.donut.global.jwt.MyJwtProvider;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController {
    
    private final UserService userService;

    @Value("${meta.name}")
    private String name;

    @PostMapping("/join")
    public ResponseEntity<?> join(@RequestBody @Valid UserReq.JoinDTO joinDTO, BindingResult bindingResult) {
        String jwt = userService.회원가입(joinDTO);
        return ResponseEntity.ok().header(MyJwtProvider.HEADER, jwt).body("회원가입 완료");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid UserReq.LoginDTO loginDTO, BindingResult bindingResult) {
        String jwt = userService.로그인(loginDTO);
        return ResponseEntity.ok().header(MyJwtProvider.HEADER, jwt).body("로그인 완료");
    }

    @PutMapping("/users/update")
    public ResponseEntity<?> update(@AuthenticationPrincipal MyUserDetails myUserDetails, BindingResult bindingResult, @RequestBody @Valid UserReq.UpdateDTO updateDTO) {
        UserResp.UpdateDTO resp = userService.회원수정(myUserDetails, updateDTO);
        return ResponseEntity.ok(resp);
    }
}
