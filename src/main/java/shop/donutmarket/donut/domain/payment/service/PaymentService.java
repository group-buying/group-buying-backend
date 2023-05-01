package shop.donutmarket.donut.domain.payment.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import shop.donutmarket.donut.domain.board.model.Event;
import shop.donutmarket.donut.domain.board.repository.EventRepository;
import shop.donutmarket.donut.domain.participant.model.Participant;
import shop.donutmarket.donut.domain.participant.repository.ParticipantRepository;
import shop.donutmarket.donut.domain.payment.dto.PaymentInfoReq;
import shop.donutmarket.donut.domain.payment.dto.PaymentReq;
import shop.donutmarket.donut.domain.payment.dto.PaymentResp;
import shop.donutmarket.donut.domain.payment.model.Payment;
import shop.donutmarket.donut.domain.payment.repository.PaymentInfoRepository;
import shop.donutmarket.donut.domain.payment.repository.PaymentRepository;
import shop.donutmarket.donut.domain.user.model.User;
import shop.donutmarket.donut.domain.user.repository.UserRepository;
import shop.donutmarket.donut.global.exception.Exception404;
import shop.donutmarket.donut.global.exception.Exception500;

import java.util.List;
import java.util.Optional;
import shop.donutmarket.donut.domain.payment.dto.PaymentReq;
import shop.donutmarket.donut.domain.payment.dto.PaymentResp.AdminSearchPaymentDTO;
import shop.donutmarket.donut.domain.payment.repository.PaymentRepository;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;

    private final EventRepository eventRepository;

    @Transactional
    public PaymentResp.insertDTO 결제데이터저장(Long eventId, Long userId, PaymentReq.insertDTO insertDTO) {
        Optional<Event> eventOP = eventRepository.findById(eventId);
        if (eventOP.isEmpty()) {
            throw new Exception404("존재하지 않는 이벤트입니다");
        }
        try {
            insertDTO.setEventId(eventId);
            insertDTO.setUserId(userId);

            // 결제 정보 insert
            Payment payment = paymentRepository.save(insertDTO.toEntity());

            PaymentResp.insertDTO resp = new PaymentResp.insertDTO(payment);
            return resp;
        } catch (Exception e) {
            throw new Exception500("결제 데이터 저장 실패 : " + e.getMessage());
        }
    }

    public List<AdminSearchPaymentDTO> 관리자결제조회() {

        // 임시 작성
    
        return null;
    }
}
