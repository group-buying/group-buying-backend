package shop.donutmarket.donut.domain.payment.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import shop.donutmarket.donut.domain.payment.dto.PaymentReq;
import shop.donutmarket.donut.domain.payment.dto.PaymentResp;
import shop.donutmarket.donut.domain.payment.service.PaymentService;
import shop.donutmarket.donut.global.auth.MyUserDetails;
import shop.donutmarket.donut.global.dto.ResponseDTO;

@RestController
@RequiredArgsConstructor
public class PaymentController {
    
    private final PaymentService paymentService;

    @PostMapping("/payments")
    public ResponseEntity<?> insert(@AuthenticationPrincipal MyUserDetails myUserDetails,
                                    @RequestBody @Valid PaymentReq.insertDTO insertDTO, BindingResult bindingResult) {
        PaymentResp.insertDTO resp = paymentService.결제데이터저장(myUserDetails.getUser().getId(), insertDTO);
        ResponseDTO<?> responseDTO = new ResponseDTO<>(resp);
        return ResponseEntity.ok(responseDTO);
    }
}
