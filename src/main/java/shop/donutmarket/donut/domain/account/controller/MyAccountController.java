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
public class MyAccountController {

    private final MyAccountService myAccountService;

    @PostMapping("/account")
    public ResponseEntity<?> insert(@AuthenticationPrincipal MyUserDetails myUserDetails, @RequestBody @Valid AccountReq.insertDTO insertDTO) {
        AccountResp.insertDTO resp = myAccountService.계좌등록(myUserDetails.getUser().getId(), insertDTO);
        if (resp != null) {
            return ResponseEntity.ok().body(resp);
        }
        return ResponseEntity.badRequest().body("잘못된 요청입니다");
    }

    @DeleteMapping("/account")
    public ResponseEntity<?> delete(@AuthenticationPrincipal MyUserDetails myUserDetails) {
        int result = myAccountService.계좌삭제(myUserDetails.getUser().getId());
        if (result == 1) {
            return ResponseEntity.ok().body("계좌가 삭제되었습니다");
        }
        return ResponseEntity.badRequest().body("잘못된 요청입니다");
    }

    @PutMapping("/account")
    public ResponseEntity<?> update(@AuthenticationPrincipal MyUserDetails myUserDetails, @RequestBody AccountReq.updateDTO updateDTO) {
        AccountResp.updateDTO resp = myAccountService.계좌수정(myUserDetails.getUser().getId(), updateDTO);
        if (resp != null) {
            return ResponseEntity.ok().body(resp);
        }
        return ResponseEntity.badRequest().body("잘못된 요청입니다");
    }

    @GetMapping("/account")
    public ResponseEntity<?> select(@AuthenticationPrincipal MyUserDetails myUserDetails) {
        AccountResp.selectDTO resp = myAccountService.계좌조회(myUserDetails.getUser().getId());
        if (resp != null) {
            return ResponseEntity.ok().body(resp);
        }
        return ResponseEntity.badRequest().body("잘못된 요청입니다");
    }
}
