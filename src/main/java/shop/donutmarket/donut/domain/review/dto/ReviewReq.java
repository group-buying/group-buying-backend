package shop.donutmarket.donut.domain.review.dto;

import lombok.Getter;
import lombok.Setter;
import shop.donutmarket.donut.domain.user.model.User;

public class ReviewReq {
    
    @Getter
    @Setter
    public static class ReviewSaveReqDTO {
        @NotBlank
        private User reviewer;
        @NotBlank
        private Long boardId;
        @NotBlank(min = 1, max = 5)
        private int score;
        @NotBlank
        private String comment;
    }
}
