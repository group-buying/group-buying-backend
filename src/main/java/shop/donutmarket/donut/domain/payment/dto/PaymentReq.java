package shop.donutmarket.donut.domain.payment.dto;



import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import shop.donutmarket.donut.domain.board.model.Event;
import shop.donutmarket.donut.domain.payment.model.Payment;
import shop.donutmarket.donut.domain.user.model.User;

public class PaymentReq {
    @Getter
    @Setter
    public static class insertDTO {
        @NotNull(message = "이벤트ID를 입력해주세요.")
        private Long eventId;
        @NotBlank(message = "결제방식을 입력해주세요.")
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
