package shop.donutmarket.donut.domain.board.service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import shop.donutmarket.donut.domain.board.dto.BoardReq.BoardDeleteReqDTO;
import shop.donutmarket.donut.domain.board.dto.BoardReq.BoardSaveReqDTO;
import shop.donutmarket.donut.domain.board.dto.BoardReq.BoardUpdateReqDTO;
import shop.donutmarket.donut.domain.board.dto.BoardResp.AdminSearchBoardDTO;
import shop.donutmarket.donut.domain.board.dto.BoardResp.BoardSaveRespDTO;
import shop.donutmarket.donut.domain.board.dto.BoardResp.BoardUpdateRespDTO;
import shop.donutmarket.donut.domain.board.model.Board;
import shop.donutmarket.donut.domain.board.model.Event;
import shop.donutmarket.donut.domain.board.model.Tag;
import shop.donutmarket.donut.domain.board.repository.BoardRepository;
import shop.donutmarket.donut.domain.board.repository.EventRepository;
import shop.donutmarket.donut.domain.board.repository.TagRepository;
import shop.donutmarket.donut.domain.user.model.User;
import shop.donutmarket.donut.global.auth.MyUserDetails;
import shop.donutmarket.donut.global.exception.*;
import shop.donutmarket.donut.global.util.MyBase64Decoder;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final EventRepository eventRepository;
    private final TagRepository tagRepository;

    @Transactional
    public BoardSaveRespDTO 게시글작성(BoardSaveReqDTO boardSaveReqDTO, @AuthenticationPrincipal MyUserDetails myUserDetails) {
        try {
            // event 먼저 save
            Event event = boardSaveReqDTO.toEventEntity();
            event = eventRepository.save(event);
            User user = myUserDetails.getUser();

            // image base64화
            String image = MyBase64Decoder.saveImage(boardSaveReqDTO.getImg());
            Board board = boardRepository.save(boardSaveReqDTO.toBoardEntity(event, image, user));

            // tag save
            List<Tag> tagList = new ArrayList<>();
            for (String comment : boardSaveReqDTO.getComment()) {
                Tag tag = Tag.builder().boardId(board.getId()).comment(comment)
                        .createdAt(LocalDateTime.now()).build();
                tagRepository.save(tag);
                tagList.add(tag);
            }

            BoardSaveRespDTO boardSaveRespDTO = new BoardSaveRespDTO(board, tagList);
            return boardSaveRespDTO;
        } catch (Exception e) {
            throw new Exception500("게시글 작성 실패 : " + e.getMessage());
        }
    }

    @Transactional(readOnly = true)
    public Board 게시글상세보기(Long id) {
        Optional<Board> boardOp = boardRepository.findByIdWithAll(id);
        if (boardOp.isEmpty()) {
            throw new Exception404("존재하지 않는 게시글입니다");
        }

        Board boardPS = boardOp.get();

        if (boardPS.getStatusCode().getId() == 203) {
            throw new Exception400("이미 삭제된 게시글입니다");
        }

        try {
            User organizer = boardPS.getOrganizer();
            Event event = boardPS.getEvent();
            Board board = Board.builder().id(boardPS.getId()).category(boardPS.getCategory()).title(boardPS.getTitle())
                    .organizer(organizer).content(boardPS.getContent()).img(boardPS.getImg()).event(event).statusCode(boardPS.getStatusCode())
                    .state(boardPS.getState()).city(boardPS.getCity()).town(boardPS.getTown()).createdAt(boardPS.getCreatedAt()).build();
            return board;
        } catch (Exception e) {
            throw new Exception500("게시글 상세보기 실패 : " + e.getMessage());
        }
    }

    @Transactional
    public BoardUpdateRespDTO 게시글수정(BoardUpdateReqDTO boardUpdateReqDTO, @AuthenticationPrincipal MyUserDetails myUserDetails) {
        User userOP = myUserDetails.getUser();
        Optional<Board> boardOP = boardRepository.findByIdWithEvent(boardUpdateReqDTO.getId());
        if (boardOP.isEmpty()) {
            throw new Exception404("존재하지 않는 게시글입니다");
        }
        Board boardPS = boardOP.get();

        if (boardPS.getStatusCode().getId() == 203) {
            throw new Exception400("이미 삭제된 게시글입니다");
        }

        // 권한 체크
        if (!Objects.equals(boardPS.getOrganizer().getId(), userOP.getId())) {
            throw new Exception403("게시글을 수정할 권한이 없습니다");
        }
        try {
            boardPS.getEvent().updateEvent(
                    boardUpdateReqDTO.getQty(), boardUpdateReqDTO.getPaymentType(),
                    boardUpdateReqDTO.getStartAt(), boardUpdateReqDTO.getEndAt()
            );

            List<String> tagList = new ArrayList<>();

            tagRepository.deleteAllByBoardId(boardUpdateReqDTO.getId());
            for (String comment : boardUpdateReqDTO.getComment()) {
                if (comment == null) {
                    break;
                }
                Tag tag = Tag.builder().boardId(boardPS.getId()).comment(comment)
                        .createdAt(LocalDateTime.now()).build();
                tagRepository.save(tag);
                tagList.add(comment);
            }

            BoardUpdateRespDTO boardUpdateRespDTO = new BoardUpdateRespDTO();
            boardUpdateRespDTO.updateRespDTO(boardUpdateReqDTO.getQty(), boardUpdateReqDTO.getPaymentType(),
                    boardUpdateReqDTO.getStartAt(), boardUpdateReqDTO.getEndAt(), boardUpdateReqDTO.getPrice(), tagList);

            return boardUpdateRespDTO;
        } catch (Exception e) {
            throw new Exception500("게시글 수정하기 실패 : " + e.getMessage());
        }
    }


    @Transactional
    public void 게시글삭제(BoardDeleteReqDTO boardDeleteReqDTO, @AuthenticationPrincipal MyUserDetails myUserDetails) {

        User userOP = myUserDetails.getUser();
        Optional<Board> boardOP = boardRepository.findById(boardDeleteReqDTO.getBoardId());
        if (boardOP.isEmpty()) {
            throw new Exception404("존재하지 않는 게시글입니다");
        }

        Board boardPS = boardOP.get();

        // 권한 체크
        if (!Objects.equals(boardPS.getOrganizer().getId(), userOP.getId())) {
            throw new Exception403("게시글을 삭제할 권한이 없습니다");
        }

        if (boardPS.getStatusCode().getId() == 203) {
            throw new Exception400("이미 삭제된 게시글입니다");
        }

        try {
            boardPS.deleteBoard();
        } catch (Exception e) {
            throw new Exception500("게시글 삭제하기 실패 : " + e.getMessage());
        }
    }

    public List<AdminSearchBoardDTO> 공고조회() {
        List<Board> boardlist = boardRepository.findAllBoardWithAllArg();
        List<AdminSearchBoardDTO> listDTO = new ArrayList<>();
        for (Board board : boardlist) {
            AdminSearchBoardDTO boardDTO = new AdminSearchBoardDTO(
                board.getId(), board.getTitle(), board.getOrganizer().getName(),
                board.getState()+" "+board.getCity()+" "+board.getTown(), board.getCreatedAt());
            listDTO.add(boardDTO); 
        }
        return listDTO;
    }
}
