package shop.donutmarket.donut.domain.payment.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import shop.donutmarket.donut.domain.board.model.Event;
import shop.donutmarket.donut.domain.board.repository.EventRepository;
import shop.donutmarket.donut.domain.payment.dto.PaymentReq;
import shop.donutmarket.donut.domain.payment.dto.PaymentResp;
import shop.donutmarket.donut.domain.payment.model.Payment;
import shop.donutmarket.donut.domain.payment.repository.PaymentRepository;
import shop.donutmarket.donut.domain.user.model.User;
import shop.donutmarket.donut.domain.user.repository.UserRepository;
import shop.donutmarket.donut.global.exception.Exception400;
import shop.donutmarket.donut.global.exception.Exception404;
import shop.donutmarket.donut.global.exception.Exception500;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;

    private final EventRepository eventRepository;

    private final UserRepository userRepository;

    @Transactional
    public PaymentResp.insertDTO 결제데이터저장(Long userId, PaymentReq.insertDTO insertDTO) {
        Optional<User> userOP = userRepository.findByIdJoinFetch(userId);
        if (userOP.isEmpty()) {
            throw new Exception400("로그인을 해주세요");
        }

        Optional<Event> eventOP = eventRepository.findById(insertDTO.getEventId());
        if (eventOP.isEmpty()) {
            throw new Exception404("존재하지 않는 이벤트입니다");
        }

        try {
            User userPS = userOP.get();
            Event eventPS = eventOP.get();

            // 결제 정보 insert
            Payment payment = paymentRepository.save(insertDTO.toEntity(eventPS, userPS));

            PaymentResp.insertDTO resp = new PaymentResp.insertDTO(payment);
            return resp;
        } catch (Exception e) {
            throw new Exception500("결제 데이터 저장 실패 : " + e.getMessage());
        }
    }
}
