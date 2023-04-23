package shop.donutmarket.donut.domain.payment.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import shop.donutmarket.donut.domain.participant.model.Participant;
import shop.donutmarket.donut.domain.participant.repository.ParticipantRepository;
import shop.donutmarket.donut.domain.payment.dto.PaymentInfoReq;
import shop.donutmarket.donut.domain.payment.dto.PaymentReq;
import shop.donutmarket.donut.domain.payment.repository.PaymentInfoRepository;
import shop.donutmarket.donut.domain.payment.repository.PaymentRepository;
import shop.donutmarket.donut.domain.user.model.User;
import shop.donutmarket.donut.domain.user.repository.UserRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PaymentService {
    
    private final PaymentRepository paymentRepository;

    @Transactional
    public void 결제성공(Long eventId, Long userId, PaymentReq.insertDTO insertDTO) {
        insertDTO.setEventId(eventId);
        insertDTO.setUserId(userId);
        // 결제 정보 insert
        paymentRepository.save(insertDTO.toEntity());
    }
}
