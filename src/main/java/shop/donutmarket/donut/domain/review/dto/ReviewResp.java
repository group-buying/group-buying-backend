package shop.donutmarket.donut.domain.review.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import shop.donutmarket.donut.domain.review.model.Review;

public class ReviewResp {
    
    @Getter
    @Setter
    @AllArgsConstructor
    public static class ReviewSaveRespDTO {
        private int score;
        private String comment;
    }
}
