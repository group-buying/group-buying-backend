package shop.donutmarket.donut.domain.payment.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;
import shop.donutmarket.donut.domain.payment.dto.PaymentInfoReq;
import shop.donutmarket.donut.domain.payment.dto.PaymentReq;
import shop.donutmarket.donut.domain.payment.dto.PaymentResp;
import shop.donutmarket.donut.domain.payment.service.PaymentService;
import shop.donutmarket.donut.global.auth.MyUserDetails;

@RestController
@RequiredArgsConstructor
public class PaymentController {
    
    private final PaymentService paymentService;

    @PostMapping("/payments")
    public ResponseEntity<?> insert(@AuthenticationPrincipal MyUserDetails myUserDetails,
                                    @RequestBody PaymentReq.insertDTO insertDTO) {
        PaymentResp.insertDTO resp = paymentService.결제데이터저장(myUserDetails.getUser().getId(), insertDTO);
        return ResponseEntity.ok(resp);
    }
}
