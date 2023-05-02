package shop.donutmarket.donut.domain.report.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import shop.donutmarket.donut.domain.myPage.dto.MyPageResp;
import shop.donutmarket.donut.domain.report.model.Report;

import java.util.List;

public interface ReportRepository extends JpaRepository<Report, Long>{

    @Query("select p from Report p left join fetch p.reporter er left join fetch er.rate " +
            "left join fetch p.reported ed left join fetch ed.rate left join fetch p.board b " +
            "left join fetch b.category left join fetch b.event left join fetch b.organizer " +
            "where p.reporter.id = :reporterId")
    List<Report> findByReporterId(@Param("reporterId") Long reporterId);
    
}
