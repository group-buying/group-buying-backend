package shop.donutmarket.donut.domain.report.dto;

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
        private String title;
        private String content;
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
