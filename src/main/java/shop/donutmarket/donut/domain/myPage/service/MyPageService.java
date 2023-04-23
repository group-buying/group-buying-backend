package shop.donutmarket.donut.domain.myPage.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.donutmarket.donut.domain.board.repository.BoardRepository;
import shop.donutmarket.donut.global.auth.MyUserDetails;

@Service
@RequiredArgsConstructor
public class MyPageService {

    private final BoardRepository boardRepository;

    @Transactional(readOnly = true)
    public void 나의게시글보기(Long id) {
        boardRepository.findByOrganizerId(id);

    }

}
