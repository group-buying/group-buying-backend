package shop.donutmarket.donut.domain.review.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import shop.donutmarket.donut.domain.user.model.User;

public class ReviewReq {
    
    @Getter
    @Setter
    public static class ReviewSaveReqDTO {
        @NotNull
        private User reviewedUser;
        @NotNull
        private Long boardId;
        @NotNull
        @Size(min = 1, max = 5)
        private int score;
        @NotBlank
        private String comment;
    }
}
