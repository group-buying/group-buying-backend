package shop.donutmarket.donut.domain.report.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import shop.donutmarket.donut.domain.myPage.dto.MyPageResp;
import shop.donutmarket.donut.domain.report.model.Report;

import java.util.List;

public interface ReportRepository extends JpaRepository<Report, Long>{

    @Query("select p from Report p where p.reporter.id = :reporterId")
    List<MyPageResp.MyReportDTO> findByReporterId(@Param("reporterId") Long reporterId);
    
}
