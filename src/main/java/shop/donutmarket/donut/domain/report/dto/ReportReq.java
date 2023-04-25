package shop.donutmarket.donut.domain.report.dto;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import shop.donutmarket.donut.domain.admin.model.StatusCode;
import shop.donutmarket.donut.domain.board.model.Board;
import shop.donutmarket.donut.domain.report.model.Report;
import shop.donutmarket.donut.domain.user.model.User;

@Getter
@Setter
public class ReportReq {

    @Getter
    @Setter
    public static class insertDTO {
        private Board board;
        private String title;
        private String content;
        private String reportType;
        private StatusCode statusCode;

        public Report toEntity() {
            return Report.builder()
                    .board(board)
                    .title(title)
                    .content(content)
                    .reportType(reportType)
                    .build();
        }
    }
}
