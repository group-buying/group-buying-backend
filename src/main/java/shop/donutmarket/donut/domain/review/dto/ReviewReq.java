package shop.donutmarket.donut.domain.review.dto;

import jakarta.validation.constraints.NotBlank;
<<<<<<< HEAD
import jakarta.validation.constraints.NotNull;
=======
import jakarta.validation.constraints.Size;
>>>>>>> 45375e1 (Feat: PaymentInfoReq, PaymentInfoRepository 생성)
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
<<<<<<< HEAD
        @NotNull
=======
        @Size(min = 1, max = 5)
>>>>>>> 45375e1 (Feat: PaymentInfoReq, PaymentInfoRepository 생성)
        private int score;
        @NotBlank
        private String comment;
    }
}
