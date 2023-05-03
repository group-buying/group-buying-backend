package shop.donutmarket.donut.domain.payment.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import shop.donutmarket.donut.domain.payment.model.PaymentData;
import shop.donutmarket.donut.domain.payment.model.PaymentInfo;

public class PaymentInfoReq {

    @Getter
    @Setter
    public static class insertDTO {
        private String event;
        private PaymentData data;
        @NotNull(message = "이벤트ID를 입력해주세요.")
        private Long eventId;
        @NotNull(message = "유저ID를 입력해주세요.")
        private Long userId;

        public PaymentInfo toEntity() {
            return PaymentInfo.builder()
                    .eventId(eventId)
                    .userId(userId)
                    .event(event)
                    .data(data)
                    .build();
        }
    }
}