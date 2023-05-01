package shop.donutmarket.donut.domain.report.service;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import shop.donutmarket.donut.domain.report.dto.ReportReq;
import shop.donutmarket.donut.domain.report.dto.ReportResp;
import shop.donutmarket.donut.domain.report.model.Report;
import shop.donutmarket.donut.domain.report.repository.ReportRepository;
import shop.donutmarket.donut.domain.user.model.User;
import shop.donutmarket.donut.domain.user.repository.UserRepository;
import shop.donutmarket.donut.global.auth.MyUserDetails;
import shop.donutmarket.donut.global.exception.Exception404;
import shop.donutmarket.donut.global.exception.Exception500;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final ReportRepository reportRepository;
    private final UserRepository userRepository;

    @Transactional
    public ReportResp.InsertRespDTO 신고하기(MyUserDetails myUserDetails, Long reportedId, ReportReq.insertDTO insertDTO) {

        Optional<User> reportedOP = userRepository.findById(reportedId);

        if (reportedOP.isEmpty()) {
            throw new Exception404("존재하지 않는 사용자입니다");
        }

        try {
            User reporterPS = myUserDetails.getUser();
            User reportedPS = reportedOP.get();
            Report report = Report.builder()
                    .reporter(reporterPS)
                    .reported(reportedPS)
                    .board(insertDTO.getBoard())
                    .title(insertDTO.getTitle())
                    .content(insertDTO.getContent())
                    .reportType(insertDTO.getReportType())
                    .build();

            Report reportPS = reportRepository.save(report);
            ReportResp.InsertRespDTO insertRespDTO = new ReportResp.InsertRespDTO(reportPS);
            return insertRespDTO;
        } catch (Exception e) {
            throw new Exception500("신고하기 실패 : " + e.getMessage());
        }
    }
}
