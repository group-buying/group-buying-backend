package shop.donutmarket.donut.domain.main.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import shop.donutmarket.donut.domain.board.model.Board;
import shop.donutmarket.donut.domain.board.repository.BoardRepository;
import shop.donutmarket.donut.domain.main.dto.MainResp;
import shop.donutmarket.donut.domain.myCategory.model.MyCategory;
import shop.donutmarket.donut.domain.myCategory.repository.MyCategoryRepository;
import shop.donutmarket.donut.domain.myLocation.model.MyLocation;
import shop.donutmarket.donut.domain.myLocation.repository.MyLocationRepository;
import shop.donutmarket.donut.domain.user.model.User;
import shop.donutmarket.donut.global.auth.MyUserDetails;
import shop.donutmarket.donut.global.exception.Exception404;
import shop.donutmarket.donut.global.exception.Exception500;

@RequiredArgsConstructor
@Service
public class MainService {

    private final MyCategoryRepository myCategoryRepository;
    private final MyLocationRepository myLocationRepository;
    private final BoardRepository boardRepository;

    @Transactional(readOnly = true)
    public MainResp 게시글목록보기(Long userId) {
        try {
            List<MyCategory> myCategoryListPS = myCategoryRepository.findByUserId(userId);
            MyLocation myLocationPS = myLocationRepository.findTownByUserId(userId);
            MainResp.MyLocationDTO myLocationDTO = new MainResp.MyLocationDTO(myLocationPS);
            List<Board> boards = boardRepository.findBoardAndStatusCode();
            MainResp mainResp = new MainResp(myCategoryListPS, myLocationDTO, boards);
            return mainResp;
        } catch (Exception e) {
            throw new Exception500("게시글 목록보기 실패 : " + e.getMessage());
        }
    }

    @Transactional(readOnly = true)
    public List<Board> 게시글필터(@AuthenticationPrincipal MyUserDetails myUserDetails, Long categoryId) {
        try {
            User user = myUserDetails.getUser();
            Optional<MyLocation> userLocationOP = myLocationRepository.findByUserId(user.getId());
            if (userLocationOP.isEmpty()) {
                throw new Exception404("내 위치가 없습니다.");
            }
            MyLocation userLocationPS = userLocationOP.get();

            List<Board> boardResult = boardRepository.findByMain(userLocationPS.getState(), userLocationPS.getCity(), userLocationPS.getTown(), categoryId);
            if (boardResult.isEmpty()) {
                throw new Exception404("검색에 맞는 결과가 없습니다");
            }
            return boardResult;
        } catch (Exception e) {
            throw new Exception500("게시글 목록보기 실패 : " + e.getMessage());
        }
    }
}
