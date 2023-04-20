package shop.donutmarket.donut.domain.review.dto;

import lombok.Getter;
import lombok.Setter;
import shop.donutmarket.donut.domain.user.model.User;

public class ReviewReq {
    
    @Getter
    @Setter
    public static class ReviewSaveReqDTO {
        private User reviewer;
        private Long boardId;
        private int score;
        private String comment;
    }
}
