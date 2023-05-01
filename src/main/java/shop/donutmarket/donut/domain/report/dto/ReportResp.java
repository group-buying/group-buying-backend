package shop.donutmarket.donut.domain.report.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import shop.donutmarket.donut.domain.report.model.Report;

@Getter
@Setter
public class ReportResp {

    @Getter
    @Setter
    public static class InsertRespDTO{
        private Report report;

        public InsertRespDTO(Report report) {
            this.report = report;
        }
    }

    @Getter
    @AllArgsConstructor
    public static class AdminSearchReportDTO{
        private Long id;
        private String type;
        private String title;
        private String reporter;
        private String reported;
        private LocalDateTime createdAt;
    }

    @Getter
    public static class AdminSearchReportDetailDTO {
        private Long id;
        private String reporter;
        private Long reportedId;
        private String reported;
        private String reportType;
        private String reportTitle;
        private String reportContent;
        private LocalDateTime reportCreatedAt;
        private Long boardId;
        private String boardTitle;
        private String boardContent;
        private String boardImg;
        private LocalDateTime boardCreatedAt;

        @Builder
        public AdminSearchReportDetailDTO(Long id, String reporter, String reported, String reportType,
                String reportTitle, String reportContent, LocalDateTime reportCreatedAt, Long boardId, String boardTitle,
                String boardContent, String boardImg, LocalDateTime boardCreatedAt) {
            this.id = id;
            this.reporter = reporter;
            this.reported = reported;
            this.reportType = reportType;
            this.reportTitle = reportTitle;
            this.reportContent = reportContent;
            this.reportCreatedAt = reportCreatedAt;
            this.boardId = boardId;
            this.boardTitle = boardTitle;
            this.boardContent = boardContent;
            this.boardImg = boardImg;
            this.boardCreatedAt = boardCreatedAt;
        }
    }
}
