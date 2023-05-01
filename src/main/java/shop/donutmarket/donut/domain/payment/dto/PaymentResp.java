package shop.donutmarket.donut.domain.payment.dto;

import lombok.Getter;
import lombok.Setter;
import shop.donutmarket.donut.domain.payment.model.Payment;

public class PaymentResp {

    @Getter
    @Setter
    public static class insertDTO {
        private Payment payment;

        public insertDTO(Payment payment) {
            this.payment = payment;
        }
    }

}
