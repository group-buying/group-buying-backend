package shop.donutmarket.donut.domain.myPage.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.donutmarket.donut.domain.blacklist.repository.BlackListRepository;
import shop.donutmarket.donut.domain.board.repository.BoardRepository;
import shop.donutmarket.donut.domain.myPage.dto.MyPageResp;
import shop.donutmarket.donut.domain.payment.repository.PaymentRepository;
import shop.donutmarket.donut.domain.report.repository.ReportRepository;
import shop.donutmarket.donut.global.auth.MyUserDetails;
import shop.donutmarket.donut.global.exception.Exception500;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MyPageService {

    private final BoardRepository boardRepository;

    private final PaymentRepository paymentRepository;

    private final BlackListRepository blackListRepository;

    private final ReportRepository reportRepository;

    @Transactional(readOnly = true)
    public List<MyPageResp.MyBoardDTO> 나의게시글보기(Long id) {
        try {
            List<MyPageResp.MyBoardDTO> myBoardDTOS = boardRepository.findByOrganizerId(id);
            return myBoardDTOS;
        } catch (Exception e) {
            throw new Exception500("나의 게시글 보기 실패 : " + e.getMessage());
        }
    }

    @Transactional(readOnly = true)
    public List<MyPageResp.MyPaymentDTO> 나의구매내역보기(Long id) {
        try {
            List<MyPageResp.MyPaymentDTO> myPaymentDTOS = paymentRepository.findByUserId(id);
            return myPaymentDTOS;
        } catch (Exception e) {
            throw new Exception500("나의 구매내역 보기 실패 : " + e.getMessage());
        }
    }

    @Transactional(readOnly = true)
    public List<MyPageResp.MyBlacklistDTO> 나의블랙리스트보기(Long id) {
        try {
            List<MyPageResp.MyBlacklistDTO> myBlacklistDTOS = blackListRepository.findByUserId(id);
            return myBlacklistDTOS;
        } catch (Exception e) {
            throw new Exception500("나의 블랙리스트 보기 실패 : " + e.getMessage());
        }
    }

    @Transactional(readOnly = true)
    public List<MyPageResp.MyReportDTO> 나의신고내역보기(Long id) {
        try {
            List<MyPageResp.MyReportDTO> myReportDTOS = reportRepository.findByReporterId(id);
            return myReportDTOS;
        } catch (Exception e) {
            throw new Exception500("나의 신고내역 보기 실패 : " + e.getMessage());
        }
    }
}
