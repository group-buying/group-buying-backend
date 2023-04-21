package shop.donutmarket.donut.domain.review.dto;

import jakarta.validation.constraints.NotBlank;
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
        @NotBlank
        private int score;
        @NotBlank
        private String comment;
    }
}
