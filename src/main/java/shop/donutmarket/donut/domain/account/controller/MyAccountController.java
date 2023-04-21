package shop.donutmarket.donut.domain.account.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;
import shop.donutmarket.donut.domain.account.dto.AccountReq;
import shop.donutmarket.donut.domain.account.dto.AccountResp;
import shop.donutmarket.donut.domain.account.model.MyAccount;
import shop.donutmarket.donut.domain.account.service.MyAccountService;
import shop.donutmarket.donut.global.auth.MyUserDetails;
import shop.donutmarket.donut.global.jwt.MyJwtProvider;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
//@RequestMapping(value = "/account")
public class MyAccountController {

    private final MyAccountService myAccountService;

    @PostMapping("/account")
    public ResponseEntity<?> insert(@AuthenticationPrincipal MyUserDetails myUserDetails, @RequestBody @Valid AccountReq.insertDTO insertDTO) {
        Optional<MyAccount> myAccountOP = myAccountService.계좌등록(myUserDetails, insertDTO);
        if (myAccountOP.isPresent()) {
            MyAccount myAccountPS = myAccountOP.get();
            AccountResp.insertDTO resp = new AccountResp.insertDTO();
            resp.setId(myAccountPS.getId());
            resp.setUserId(myAccountPS.getUser().getId());
            resp.setBrand(myAccountPS.getBrand());
            resp.setAccountNumber(myAccountPS.getAccountNumber());
            return ResponseEntity.ok().body(resp);
        }
        return ResponseEntity.badRequest().body("잘못된 요청입니다");
    }
}
