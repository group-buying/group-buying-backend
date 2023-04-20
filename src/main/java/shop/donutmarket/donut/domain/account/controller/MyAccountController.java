package shop.donutmarket.donut.domain.account.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import shop.donutmarket.donut.domain.account.service.MyAccountService;

@RestController
@RequiredArgsConstructor
@RequestMapping("account")
public class MyAccountController {
    
    private final MyAccountService myAccountService;

    @GetMapping("/")
    public void accountForm() {

    }

    @PostMapping()
    public void insert() {

    }
}
