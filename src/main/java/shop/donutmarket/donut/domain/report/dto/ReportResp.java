package shop.donutmarket.donut.domain.report.dto;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import shop.donutmarket.donut.domain.admin.model.StatusCode;
import shop.donutmarket.donut.domain.board.model.Board;
import shop.donutmarket.donut.domain.report.model.Report;
import shop.donutmarket.donut.domain.user.model.User;

import java.time.LocalDateTime;

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
