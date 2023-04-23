package shop.donutmarket.donut.domain.myPage.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.donutmarket.donut.domain.board.repository.BoardRepository;
import shop.donutmarket.donut.domain.myPage.dto.MyPageResp;
import shop.donutmarket.donut.domain.payment.repository.PaymentRepository;
import shop.donutmarket.donut.global.auth.MyUserDetails;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MyPageService {

    private final BoardRepository boardRepository;

    private final PaymentRepository paymentRepository;

    @Transactional(readOnly = true)
    public List<MyPageResp.MyBoardDTO> 나의게시글보기(Long id) {
        List<MyPageResp.MyBoardDTO> myBoardDTO = boardRepository.findByOrganizerId(id);
        return myBoardDTO;
    }
    @Transactional(readOnly = true)
    public void 나의구매내역보기(Long id) {
        List<MyPageResp.MyBoardDTO> myBoardDTO = paymentRepository.findByOrganizerId(id);
        return myBoardDTO;
    }

}
