package shop.donutmarket.donut.domain.report.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import shop.donutmarket.donut.domain.report.dto.ReportReq;
import shop.donutmarket.donut.domain.report.dto.ReportResp;
import shop.donutmarket.donut.domain.report.model.Report;
import shop.donutmarket.donut.domain.report.repository.ReportRepository;
import shop.donutmarket.donut.domain.user.model.User;
import shop.donutmarket.donut.domain.user.repository.UserRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReportService {
    
    private final ReportRepository reportRepository;
    private final UserRepository userRepository;

    @Transactional
    public ReportResp.InsertRespDTO 신고하기(Long reporterId, Long reportedId, ReportReq.insertDTO insertDTO) {
        try {
            Optional<User> reporterOP = userRepository.findById(reporterId);
            Optional<User> reportedOP = userRepository.findById(reportedId);
            if (reporterOP.isPresent() && reportedOP.isPresent()) {
                User reporterPS = reporterOP.get();
                User reportedPS = reportedOP.get();
                Report report = Report.builder()
                        .reporter(reporterPS)
                        .reported(reportedPS)
                        .board(insertDTO.getBoard())
                        .title(insertDTO.getTitle())
                        .content(insertDTO.getContent())
                        .reportType(insertDTO.getReportType())
                        .statusCode(insertDTO.getStatusCode())
                        .build();
                Report reportPS = reportRepository.save(report);
                ReportResp.InsertRespDTO insertRespDTO = new ReportResp.InsertRespDTO(reportPS);
                return insertRespDTO;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
