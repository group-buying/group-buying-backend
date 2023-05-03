package shop.donutmarket.donut.domain.review.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

public class ReviewReq {
    
    @Getter
    @Setter
    public static class ReviewSaveReqDTO {
        @NotNull(message = "리뷰대상자의 ID를 입력해주세요.")
        private Long reviewedUserId;
        @NotNull(message = "리뷰대상 게시글 ID를 입력해주세요.")
        private Long boardId;
        @NotNull(message = "점수를 입력해주세요. (1~5)")
        @Min(1) @Max(5)
        private int score;
        @NotBlank(message = "내용을 입력해주세요.")
        private String comment;
    }
}
