package shop.donutmarket.donut.domain.report.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import shop.donutmarket.donut.domain.board.model.Board;
import shop.donutmarket.donut.domain.report.model.Report;

@Getter
@Setter
public class ReportReq {

    @Getter
    @Setter
    public static class insertDTO {
        private Long boardId;
        @NotBlank(message = "제목을 입력해주세요.")
        private String title;
        @NotBlank(message = "내용을 입력해주세요.")
        private String content;
        @NotBlank(message = "신고타입을 입력해주세요.")
        private String reportType;

        public Report toEntity(Board board) {
            return Report.builder()
                    .board(board)
                    .title(title)
                    .content(content)
                    .reportType(reportType)
                    .build();
        }
    }
}
