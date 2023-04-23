package shop.donutmarket.donut.domain.payment.dto;

import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import shop.donutmarket.donut.domain.admin.model.StatusCode;
import shop.donutmarket.donut.domain.participant.model.Participant;
import shop.donutmarket.donut.domain.payment.model.Payment;

public class PaymentReq {
    @Getter
    @Setter
    public static class insertDTO {
        private Participant participant;
        private String paymentType;
        private StatusCode statusCode;
        private boolean confirmed;

        public Payment toEntity() {
            return Payment.builder()
                    .participant(participant)
                    .paymentType(paymentType)
                    .statusCode(statusCode)
                    .confirmed(confirmed)
                    .build();
        }
    }

}
