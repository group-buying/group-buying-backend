package shop.donutmarket.donut.domain.myPage.dto;

import lombok.Getter;
import lombok.Setter;
import shop.donutmarket.donut.domain.blacklist.model.Blacklist;
import shop.donutmarket.donut.domain.board.model.Board;
import shop.donutmarket.donut.domain.payment.model.Payment;
import shop.donutmarket.donut.domain.report.model.Report;
import shop.donutmarket.donut.domain.review.model.Review;

import java.util.List;

public class MyPageResp {

    @Getter
    @Setter
    public static class MyBoardDTO {
        private List<Board> board;

        public MyBoardDTO(List<Board> board) {
            this.board = board;
        }
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
    @Getter
    @Setter
    public static class MyReviewDTO {
        private List<Review> review;

        public MyReviewDTO(List<Review> review) {
            this.review = review;
        }
    }

}
