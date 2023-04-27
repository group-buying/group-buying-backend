package shop.donutmarket.donut.domain.payment.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import shop.donutmarket.donut.domain.payment.dto.PaymentInfoReq;
import shop.donutmarket.donut.domain.payment.dto.PaymentReq;
import shop.donutmarket.donut.domain.payment.service.PaymentInfoService;
import shop.donutmarket.donut.domain.payment.service.PaymentService;
import shop.donutmarket.donut.global.auth.MyUserDetails;

@RestController
@RequiredArgsConstructor
public class PaymentInfoController {
    private final PaymentInfoService paymentInfoService;

    @PostMapping("/paymentInfo/{id}")
    public ResponseEntity<?> insert(@PathVariable Long id,
                                    @AuthenticationPrincipal MyUserDetails myUserDetails,
                                    @RequestBody PaymentInfoReq.insertDTO insertDTO) {
        paymentInfoService.부트페이데이터저장(id, myUserDetails.getUser().getId(), insertDTO);
        return ResponseEntity.ok("부트페이 결제 데이터 저장 성공");
    }
}
