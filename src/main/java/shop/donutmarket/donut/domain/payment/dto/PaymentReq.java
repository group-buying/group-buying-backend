package shop.donutmarket.donut.domain.payment.dto;

import lombok.Getter;
import lombok.Setter;
import shop.donutmarket.donut.domain.board.model.Event;
import shop.donutmarket.donut.domain.payment.model.Payment;
import shop.donutmarket.donut.domain.user.model.User;

public class PaymentReq {
    @Getter
    @Setter
    public static class insertDTO {
        private Long eventId;
        private String paymentType;
        private boolean confirmed;

        public Payment toEntity(Event event, User user) {
            return Payment.builder()
                    .event(event)
                    .user(user)
                    .paymentType(paymentType)
                    .confirmed(confirmed)
                    .build();
        }
    }

}
