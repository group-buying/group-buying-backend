package shop.donutmarket.donut.domain.user.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;
import shop.donutmarket.donut.domain.user.dto.UserRequest;
import shop.donutmarket.donut.domain.user.dto.UserResponse;
import shop.donutmarket.donut.domain.user.repository.UserRepository;
import shop.donutmarket.donut.domain.user.service.UserService;
import shop.donutmarket.donut.global.auth.MyUserDetails;
import shop.donutmarket.donut.global.dto.ResponseDTO;
import shop.donutmarket.donut.global.jwt.MyJwtProvider;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController {
    
    private final UserService userService;

    private final AuthenticationManager manager;

    private final UserRepository userRepository;

    @Value("${meta.name}")
    private String name;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserRequest.LoginDTO loginDTO) {
        String jwt = userService.로그인(loginDTO);
        return ResponseEntity.ok().header(MyJwtProvider.HEADER, jwt).body("로그인 완료");
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<?> userCheck(@AuthenticationPrincipal MyUserDetails myUserDetails) {
        Long id = myUserDetails.getUser().getId();
        String role = myUserDetails.getUser().getRole();
        return ResponseEntity.ok().body(id + " : " + role);
    }

    @GetMapping("/")
    public ResponseEntity<?> hello() {
        return ResponseEntity.ok().body(name);
    }

    @PostMapping("/join")
    public @ResponseBody ResponseEntity<?> join(@RequestBody UserRequest.JoinDTO joinDTO) {
        // select 됨
        UserResponse.JoinDTO data = userService.회원가입(joinDTO);
        // select 안됨
        ResponseDTO<?> responseDTO = new ResponseDTO<>().data(data);
        return ResponseEntity.ok().body(responseDTO);
    }
}
