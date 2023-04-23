package shop.donutmarket.donut.domain.myPage.dto;

import lombok.Getter;
import lombok.Setter;
import shop.donutmarket.donut.domain.board.model.Board;
import shop.donutmarket.donut.domain.payment.model.Payment;

public class MyPageResp {

    @Getter
    @Setter
    public static class MyBoardDTO {
        private Board board;
    }

    @Getter
    @Setter
    public static class MyPaymentDTO {
        private Payment payment;
    }
}
