package shop.donutmarket.donut.domain.payment.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.donutmarket.donut.domain.payment.dto.PaymentInfoReq;
import shop.donutmarket.donut.domain.payment.dto.PaymentReq;
import shop.donutmarket.donut.domain.payment.repository.PaymentInfoRepository;

@Service
@RequiredArgsConstructor
public class PaymentInfoService {

    private final PaymentInfoRepository paymentInfoRepository;

    @Transactional
    public void 결제데이터저장(Long eventId, Long userId, PaymentInfoReq.insertDTO insertInfoDTO) {
        insertInfoDTO.setEventId(eventId);
        insertInfoDTO.setUserId(userId);
        // 부트페이 결제 정보 insert
        paymentInfoRepository.save(insertInfoDTO.toEntity());
    }
}
