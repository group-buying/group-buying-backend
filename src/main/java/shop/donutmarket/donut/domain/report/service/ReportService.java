package shop.donutmarket.donut.domain.report.service;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import shop.donutmarket.donut.domain.report.dto.ReportReq;
import shop.donutmarket.donut.domain.report.dto.ReportResp;
import shop.donutmarket.donut.domain.report.dto.ReportResp.AdminSearchReportDTO;
import shop.donutmarket.donut.domain.report.dto.ReportResp.AdminSearchReportDetailDTO;
import shop.donutmarket.donut.domain.report.model.Report;
import shop.donutmarket.donut.domain.report.repository.ReportRepository;
import shop.donutmarket.donut.domain.user.model.User;
import shop.donutmarket.donut.domain.user.repository.UserRepository;
import shop.donutmarket.donut.global.auth.MyUserDetails;
import shop.donutmarket.donut.global.exception.Exception404;
import shop.donutmarket.donut.global.exception.Exception500;

import java.util.ArrayList;
import java.util.List;
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
                    .statusCode(insertDTO.getStatusCode())
                    .build();

            Report reportPS = reportRepository.save(report);
            ReportResp.InsertRespDTO insertRespDTO = new ReportResp.InsertRespDTO(reportPS);
            return insertRespDTO;
        } catch (Exception e) {
            throw new Exception500("신고하기 실패 : " + e.getMessage());
        }
    }

    @Transactional(readOnly = true)
    public List<AdminSearchReportDTO> 관리자신고조회() {
        try {
            List<Report> list = reportRepository.findAllWithArg();
            List<AdminSearchReportDTO> dtolist = new ArrayList<>();
            for (Report report : list) {
                AdminSearchReportDTO reportDTO = new AdminSearchReportDTO(
                    report.getId(), report.getReportType(), report.getTitle(),
                    report.getReporter().getName(), report.getReported().getName(), report.getCreatedAt());
                    dtolist.add(reportDTO);
            }
            return dtolist;
        } catch (Exception e) {
            throw new Exception500("조회에 실패하였습니다.");
        }
    }
    
    @Transactional(readOnly = true)
    public AdminSearchReportDetailDTO 관리자신고상세조회(Long id) {
        Optional<Report> reportOP = reportRepository.findByIdWithAllArg(id);
        if (!(reportOP.isPresent())) {
            throw new Exception500("존재하지 않는 신고입니다.");
        }
        Report reportPS = reportOP.get();
        try {
            if (reportPS.getBoard().getId() == null) {
                AdminSearchReportDetailDTO detailDTO = AdminSearchReportDetailDTO.builder().id(id)
                .reporter(reportPS.getReporter().getName()).reported(reportPS.getReported().getName()).reportedId(reportPS.getReported().getId())
                .reportType(reportPS.getReportType()).reportTitle(reportPS.getTitle()).reportContent(reportPS.getContent())
                .reportCreatedAt(reportPS.getCreatedAt()).statusCode(reportPS.getStatusCode().getId()).build();
                return detailDTO;
            }
            
            AdminSearchReportDetailDTO detailDTO = AdminSearchReportDetailDTO.builder().id(id)
            .reporter(reportPS.getReporter().getName()).reported(reportPS.getReported().getName()).reportedId(reportPS.getReported().getId())
            .reportType(reportPS.getReportType()).reportTitle(reportPS.getTitle()).reportContent(reportPS.getContent())
            .reportCreatedAt(reportPS.getCreatedAt()).boardId(reportPS.getBoard().getId()).boardTitle(reportPS.getBoard().getTitle())
            .boardContent(reportPS.getBoard().getContent()).boardImg(reportPS.getBoard().getImg())
            .boardCreatedAt(reportPS.getBoard().getCreatedAt()).statusCode(reportPS.getStatusCode().getId()).build();
            return detailDTO;
        } catch (Exception e) {
            throw new Exception500("상세조회에 실패하였습니다.");
        }
    }
    
    @Transactional
    public void 관리자신고처리(Long id) {
        Optional<Report> reportOP = reportRepository.findById(id);
        if (reportOP.isPresent()) {
            throw new Exception500("존재하지 않는 신고입니다.");
        }
        Report reportPS = reportOP.get();
        try {
            reportPS.processed();
        } catch (Exception e) {
            throw new Exception500("신고처리에 실패하였습니다.");
        }
    }
}
