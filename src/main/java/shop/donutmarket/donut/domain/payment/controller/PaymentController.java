package shop.donutmarket.donut.domain.payment.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import shop.donutmarket.donut.domain.payment.service.PaymentService;

@RestController
@RequiredArgsConstructor
public class PaymentController {
    
    private final PaymentService paymentService;

    @PostMapping("/payment")
    public void insert() {

    }
}
