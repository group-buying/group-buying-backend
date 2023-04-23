package shop.donutmarket.donut.domain.payment.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import shop.donutmarket.donut.domain.payment.dto.PaymentInfoReq;
import shop.donutmarket.donut.domain.payment.repository.PaymentInfoRepository;
import shop.donutmarket.donut.domain.payment.repository.PaymentRepository;

@Service
@RequiredArgsConstructor
public class PaymentService {
    
    private final PaymentRepository paymentRepository;

    private final PaymentInfoRepository paymentInfoRepository;

    @Transactional
    public void 결제성공(PaymentInfoReq.insertDTO insertDTO) {
        paymentInfoRepository.save(insertDTO.toEntity());
    }
}
