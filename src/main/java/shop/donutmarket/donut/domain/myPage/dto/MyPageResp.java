package shop.donutmarket.donut.domain.myPage.dto;

import lombok.Getter;
import lombok.Setter;
import shop.donutmarket.donut.domain.blacklist.model.Blacklist;
import shop.donutmarket.donut.domain.board.model.Board;
import shop.donutmarket.donut.domain.payment.model.Payment;
import shop.donutmarket.donut.domain.report.model.Report;

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

    @Getter
    @Setter
    public static class MyBlacklistDTO {
        private Blacklist blacklist;
    }

    @Getter
    @Setter
    public static class MyReportDTO {
        private Report report;
    }
}
