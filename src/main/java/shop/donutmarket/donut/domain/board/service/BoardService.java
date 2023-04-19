package shop.donutmarket.donut.domain.board.service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import shop.donutmarket.donut.domain.admin.model.StatusCode;
import shop.donutmarket.donut.domain.board.dto.BoardReq.BoardSaveReqDTO;
import shop.donutmarket.donut.domain.board.dto.BoardReq.BoardUpdateReqDTO;
import shop.donutmarket.donut.domain.board.dto.BoardResp.BoardSaveRespDTO;
import shop.donutmarket.donut.domain.board.model.Board;
import shop.donutmarket.donut.domain.board.model.Event;
import shop.donutmarket.donut.domain.board.model.Tag;
import shop.donutmarket.donut.domain.board.repository.BoardRepository;
import shop.donutmarket.donut.domain.board.repository.EventRepository;
import shop.donutmarket.donut.domain.board.repository.TagRepository;
import shop.donutmarket.donut.domain.user.model.User;
import shop.donutmarket.donut.global.util.MyBase64Decoder;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final EventRepository eventRepository;
    private final TagRepository tagRepository;

    @Transactional
    public BoardSaveRespDTO 공고작성(BoardSaveReqDTO boardSaveReqDTO, User user) {

        // event 먼저 save
        Event event = boardSaveReqDTO.toEventEntity();
        event = eventRepository.save(event);

        // image base64화
        // String image = null;
        // try {
        //     image = MyBase64Decoder.saveImage(boardSaveReqDTO.getImg());
        // } catch (IOException e) {
        //     // Exception 처리 필요
        // }
        Board board = boardSaveReqDTO.toBoardEntity(event, boardSaveReqDTO.getImg());
        board = boardRepository.save(board);

        // tag save
        List<Tag> tagList = new ArrayList<>();
        for (String comment : boardSaveReqDTO.getComment()) {
            Tag tag = Tag.builder().board(board).comment(comment)
            .createdAt(LocalDateTime.now()).build();
            tagRepository.save(tag);
            tagList.add(tag);
        }

        BoardSaveRespDTO boardSaveRespDTO = new BoardSaveRespDTO(board, event, tagList);

        return boardSaveRespDTO;
    }

    public Board 상세보기(Long id) {

        Board boardPS = boardRepository.findById(id).get();
        if (boardPS.getStatusCode().getId() == 203L) {
            // 해당 게시글은 삭제되었습니다. 리턴
        }
        return boardPS;
    }

    @Transactional
    public void 업데이트(BoardUpdateReqDTO boardUpdateReqDTO) {

        // 인가 체크 필요

        Board boardPS = boardRepository.findById(boardUpdateReqDTO.getId()).get();
        if (boardPS.getStatusCode().getId() == 203L) {
            // 해당 게시글은 삭제되었습니다. 리턴
        }

        Event event = boardUpdateReqDTO.toEventEntity();

        // image base64화
        String image = null;
        try {
            image = MyBase64Decoder.saveImage(boardUpdateReqDTO.getImg());
        } catch (IOException e) {
            // Exception 처리 필요
        }
        Board board = boardUpdateReqDTO.toBoardEntity(event, image);
        
        // tag는 삭제 후 재생성 수정x
        tagRepository.deleteAllByBoardId(boardUpdateReqDTO.getId());
        eventRepository.save(event);
        boardRepository.save(board);
        
        for (String comment : boardUpdateReqDTO.getComment()) {
            Tag tag = Tag.builder().board(board).comment(comment)
            .createdAt(LocalDateTime.now()).build();
            tagRepository.save(tag);
        }
        
    }

    @Transactional
    public void 삭제(Long boardId) {
        
        // 인가 체크 필요
        StatusCode deletedCode = StatusCode.builder().id(203L).type("board")
        .status("삭제").createdAt(LocalDateTime.now()).build();
        
        Board board = Board.builder().id(boardId).statusCode(deletedCode).build();
        boardRepository.save(board);
    }
}
