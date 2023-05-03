package shop.donutmarket.donut.domain.review.dto;

import lombok.Getter;
import lombok.Setter;
import shop.donutmarket.donut.domain.review.model.Review;

public class ReviewResp {
    
    @Getter
    @Setter
    public static class ReviewSaveRespDTO {
        private Review review;

        public ReviewSaveRespDTO(Review review) {
            this.review = review;
        }
    }
}
