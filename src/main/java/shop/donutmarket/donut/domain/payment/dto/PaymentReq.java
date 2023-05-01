package shop.donutmarket.donut.domain.payment.dto;

import lombok.Getter;
import lombok.Setter;
import shop.donutmarket.donut.domain.admin.model.StatusCode;
import shop.donutmarket.donut.domain.payment.model.Payment;

public class PaymentReq {
    @Getter
    @Setter
    public static class insertDTO {
        private Long userId;
        private Long eventId;
        private String paymentType;
        private StatusCode statusCode;
        private boolean confirmed;

        public Payment toEntity() {
            return Payment.builder()
                    .eventId(eventId)
                    .userId(userId)
                    .paymentType(paymentType)
                    .statusCode(statusCode)
                    .confirmed(confirmed)
                    .build();
        }
    }
}
