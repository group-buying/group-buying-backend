package shop.donutmarket.donut.domain.report.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
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
}
