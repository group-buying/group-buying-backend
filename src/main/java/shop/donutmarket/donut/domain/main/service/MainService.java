package shop.donutmarket.donut.domain.main.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.donutmarket.donut.domain.board.model.Board;
import shop.donutmarket.donut.domain.board.repository.BoardRepository;
import shop.donutmarket.donut.domain.board.repository.EventRepository;
import shop.donutmarket.donut.domain.main.dto.MainResp;
import shop.donutmarket.donut.domain.myCategory.model.MyCategory;
import shop.donutmarket.donut.domain.myCategory.repository.MyCategoryRepository;
import shop.donutmarket.donut.domain.myLocation.model.MyLocation;
import shop.donutmarket.donut.domain.myLocation.repository.MyLocationRepository;
import shop.donutmarket.donut.global.dto.ResponseDTO;
import shop.donutmarket.donut.global.exception.Exception500;

import java.util.List;
import java.util.Optional;

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
            throw new Exception500("게시글 목록 보기가 실패했습니다");
        }
    }
}
