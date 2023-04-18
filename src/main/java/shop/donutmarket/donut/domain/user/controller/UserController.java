package shop.donutmarket.donut.domain.user.controller;

import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import shop.donutmarket.donut.domain.user.service.UserService;

@RestController
@RequiredArgsConstructor
public class UserController {
    
    private final UserService userService;

    
}
