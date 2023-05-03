package shop.donutmarket.donut.domain.account.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import shop.donutmarket.donut.domain.account.dto.AccountReq;
import shop.donutmarket.donut.domain.account.dto.AccountResp;
import shop.donutmarket.donut.domain.account.service.MyAccountService;
import shop.donutmarket.donut.global.auth.MyUserDetails;

@RestController
@RequiredArgsConstructor
@RequestMapping("/accounts")
public class MyAccountController {

    private final MyAccountService myAccountService;

    @PostMapping
    public ResponseEntity<?> insert(@AuthenticationPrincipal MyUserDetails myUserDetails, @RequestBody @Valid AccountReq.insertDTO insertDTO, BindingResult bindingResult) {
        AccountResp.insertDTO resp = myAccountService.계좌등록(myUserDetails.getUser().getId(), insertDTO);
        return ResponseEntity.ok(resp);
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@AuthenticationPrincipal MyUserDetails myUserDetails) {
        myAccountService.계좌삭제(myUserDetails.getUser().getId());
        return ResponseEntity.ok("계좌 삭제 성공");
    }

    @PutMapping
    public ResponseEntity<?> update(@AuthenticationPrincipal MyUserDetails myUserDetails, @RequestBody AccountReq.updateDTO updateDTO, BindingResult bindingResult) {
        AccountResp.updateDTO resp = myAccountService.계좌수정(myUserDetails.getUser().getId(), updateDTO);
        return ResponseEntity.ok(resp);
    }

    @GetMapping
    public ResponseEntity<?> select(@AuthenticationPrincipal MyUserDetails myUserDetails) {
        AccountResp.selectDTO resp = myAccountService.계좌조회(myUserDetails.getUser().getId());
        return ResponseEntity.ok(resp);
    }
}
