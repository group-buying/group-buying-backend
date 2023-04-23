package shop.donutmarket.donut.domain.payment.dto;

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

        public PaymentInfo toEntity() {
            return PaymentInfo.builder()
                    .event(event)
                    .data(data)
                    .build();
        }
    }
}