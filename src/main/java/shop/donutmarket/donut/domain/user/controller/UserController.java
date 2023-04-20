package shop.donutmarket.donut.domain.user.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;
import shop.donutmarket.donut.domain.user.dto.UserReq;
import shop.donutmarket.donut.domain.user.repository.UserRepository;
import shop.donutmarket.donut.domain.user.service.UserService;
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
    public ResponseEntity<?> join(@RequestBody @Valid UserReq.JoinDTO joinDTO) {
        userService.회원가입(joinDTO);
        return ResponseEntity.ok().body("회원가입 완료");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid UserReq.LoginDTO loginDTO) {
        String jwt = userService.로그인(loginDTO);
        return ResponseEntity.ok().header(MyJwtProvider.HEADER, jwt).body("로그인 완료");
    }

//    @PutMapping("/update")
//    public ResponseEntity<?> update(@RequestBody @Valid UserReq.UpdateDTO updateDTO) {
//
//        return
//    }

}
