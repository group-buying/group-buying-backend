package shop.donutmarket.donut.domain.payment.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import shop.donutmarket.donut.domain.payment.dto.PaymentInfoReq;
import shop.donutmarket.donut.domain.payment.dto.PaymentReq;
import shop.donutmarket.donut.domain.payment.service.PaymentInfoService;
import shop.donutmarket.donut.domain.payment.service.PaymentService;

@RestController
@RequiredArgsConstructor
public class PaymentInfoController {
    private final PaymentInfoService paymentInfoService;

    @PostMapping("/paymentInfo")
    public ResponseEntity<?> insert(@RequestBody PaymentInfoReq.insertDTO insertDTO) {
        // participant Id 받아오기 필요함
        paymentInfoService.결제데이터저장(insertDTO);
        return ResponseEntity.ok().body("결제 데이터 저장 성공");
    }
}
