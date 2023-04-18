package shop.donutmarket.donut.domain.account.controller;

import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import shop.donutmarket.donut.domain.account.service.MyAccountService;

@RestController
@RequiredArgsConstructor
public class MyAccountController {
    
    private final MyAccountService myAccountService;
}
