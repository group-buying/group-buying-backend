package shop.donutmarket.donut.domain.payment.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.donutmarket.donut.domain.board.model.Event;
import shop.donutmarket.donut.domain.board.repository.EventRepository;
import shop.donutmarket.donut.domain.payment.dto.PaymentInfoReq;
import shop.donutmarket.donut.domain.payment.dto.PaymentReq;
import shop.donutmarket.donut.domain.payment.repository.PaymentInfoRepository;
import shop.donutmarket.donut.global.exception.Exception404;
import shop.donutmarket.donut.global.exception.Exception500;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PaymentInfoService {

    private final PaymentInfoRepository paymentInfoRepository;

    private final EventRepository eventRepository;

    @Transactional
    public void 부트페이데이터저장(Long eventId, Long userId, PaymentInfoReq.insertDTO insertInfoDTO) {
        Optional<Event> eventOP = eventRepository.findById(eventId);
        if (eventOP.isEmpty()) {
            throw new Exception404("존재하지 않는 이벤트입니다");
        }
        try {
            insertInfoDTO.setEventId(eventId);
            insertInfoDTO.setUserId(userId);
            // 부트페이 결제 정보 insert
            paymentInfoRepository.save(insertInfoDTO.toEntity());
        } catch (Exception e) {
            throw new Exception500("부트페이 결제 데이터 저장 실패 : " + e.getMessage());
        }
    }
}
