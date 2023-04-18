package shop.donutmarket.donut.domain.report;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

import shop.donutmarket.donut.domain.report.model.Report;
import shop.donutmarket.donut.domain.report.repository.ReportRepository;
import shop.donutmarket.donut.domain.user.StatusCodeConst;
import shop.donutmarket.donut.domain.user.UserConst;

@DataJpaTest
@Transactional
public class ReportRepositoryTest {
    
    @Autowired
    private ReportRepository reportRepository;

    @BeforeEach
    void setUp(){
        Report report = Report.builder().id(1L).reporter(new UserConst()).reported(new UserConst()).title("신고").content("신고내용").reportType("일반").statusCode(new StatusCodeConst()).createdAt(LocalDateTime.now()).build();
        reportRepository.save(report);
    }

    @Test
    void findById_Test(){
        // given
        Long id = 1L;
        
        // when
        Optional<Report> report = reportRepository.findById(id);
        
        // then
        assertNotNull(report);
    }
    
    @Test
    void save_Test() {
        // given
        Long id = 2L;
        Report report = Report.builder().id(id).reporter(new UserConst()).reported(new UserConst()).title("신고2").content("신고내용2").reportType("일반").statusCode(new StatusCodeConst()).createdAt(LocalDateTime.now()).build();
        
        // when
        reportRepository.save(report);

        // then
        assertNotNull(reportRepository.findById(id));
    }

    @Test
    void deleteById_Test(){
        // given
        Long id = 1L;
        
        // when
        reportRepository.deleteById(id);

        // then
        assertEquals(Optional.empty(), reportRepository.findById(id));
    }
}
