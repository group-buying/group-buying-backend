package shop.donutmarket.donut.domain.report.dto;

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
}
