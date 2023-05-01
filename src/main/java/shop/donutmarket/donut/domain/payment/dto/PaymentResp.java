package shop.donutmarket.donut.domain.payment.dto;

import lombok.Getter;
import lombok.Setter;
import shop.donutmarket.donut.domain.admin.model.StatusCode;
import shop.donutmarket.donut.domain.payment.model.Payment;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class PaymentResp {

    @Getter
    @Setter
    public static class insertDTO {
        private Payment payment;

        public insertDTO(Payment payment) {
            this.payment = payment;
        }
    }

    @AllArgsConstructor
    @NoArgsConstructor
    public static class AdminSearchPaymentDTO {
        private Long id;
        private String username;
        private String boardname;
        private int qty;
        private int price;
        private LocalDateTime createdAt;
    }
}
