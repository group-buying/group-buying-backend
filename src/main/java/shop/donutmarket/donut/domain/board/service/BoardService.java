package shop.donutmarket.donut.domain.board.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import shop.donutmarket.donut.domain.admin.model.StatusCode;
import shop.donutmarket.donut.domain.board.dto.BoardReq.BoardSaveReqDTO;
import shop.donutmarket.donut.domain.board.dto.BoardReq.BoardUpdateReqDTO;
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

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final EventRepository eventRepository;
    private final TagRepository tagRepository;

    @Transactional
    public BoardSaveRespDTO 공고작성(BoardSaveReqDTO boardSaveReqDTO, @AuthenticationPrincipal MyUserDetails myUserDetails) {

        // event 먼저 save
        Event event = boardSaveReqDTO.toEventEntity();
        event = eventRepository.save(event);
        User user = myUserDetails.getUser();
        // image base64화
        // String image = null;
        // try {
        //     image = MyBase64Decoder.saveImage(boardSaveReqDTO.getImg());
        // } catch (IOException e) {
        //     // Exception 처리 필요
        // }
        Board board = boardSaveReqDTO.toBoardEntity(event, boardSaveReqDTO.getImg(), user);
        board = boardRepository.save(board);

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
    }

    public Board 상세보기(Long id) {

        Optional<Board> boardOptional = boardRepository.findById(id);
        Board boardPS = boardOptional.get();
        // if (boardPS.getStatusCode().getId() == 203L) {
        //     // 해당 게시글은 삭제되었습니다. 리턴
        // }
        return boardPS;
    }

    @Transactional
    public BoardUpdateRespDTO 업데이트(BoardUpdateReqDTO boardUpdateReqDTO, @AuthenticationPrincipal MyUserDetails myUserDetails) {

        User userOP = myUserDetails.getUser();
        Optional<Board> boardOP = boardRepository.findById(boardUpdateReqDTO.getId());
        if(!boardOP.isPresent()){
            // 없음 예외처리
        }
        Board boardPS = boardOP.get();

        // 권한 체크
		if(!(boardPS.getOrganizer().getId() == userOP.getId())){
			// 권한 없음 처리
		}

        if (boardPS.getStatusCode().getId() == 203) {
            // 해당 게시글은 삭제되었습니다. 리턴
        }

        boardOP.get().getEvent().updateEvent(
            boardUpdateReqDTO.getQty(),boardUpdateReqDTO.getPaymentType(),
            boardUpdateReqDTO.getStartAt(),boardUpdateReqDTO.getEndAt()
        );

        // 더디체킹

        // // image base64화
        // String image = null;
        // try {
        //     image = MyBase64Decoder.saveImage(boardUpdateReqDTO.getImg());
        // } catch (IOException e) {
        //     // Exception 처리 필요
        // }

        BoardUpdateRespDTO boardUpdateRespDTO = new BoardUpdateRespDTO();
        System.out.println("Tag");
        List<Tag> tagList = new ArrayList<>();
        for (String comment : boardUpdateReqDTO.getComment()) {
            if(comment.isBlank()){
                break;
            }
            Tag tag = Tag.builder().board(boardPS).comment(comment)
            .createdAt(LocalDateTime.now()).build();
            tagRepository.save(tag);
            tagList.add(tag);
        }
        System.out.println("RespDTO");
        boardUpdateRespDTO.updateRespDTO(boardUpdateReqDTO.getQty(),boardUpdateReqDTO.getPaymentType(),
        boardUpdateReqDTO.getStartAt(),boardUpdateReqDTO.getEndAt(),boardUpdateReqDTO.getPrice(), tagList);

        return boardUpdateRespDTO;
    }
    

    @Transactional
    public void 삭제(Long boardId, @AuthenticationPrincipal MyUserDetails myUserDetails) {

        User userOP = myUserDetails.getUser();
        Optional<Board> boardOP = boardRepository.findById(boardId);
        if(!boardOP.isPresent()){
            // 없음 예외처리
        }
        Board boardPS = boardOP.get();

        // 권한 체크
		if(boardPS.getOrganizer().getId() == userOP.getId()){
			// 권한 없음 처리
		}
        // 상태코드는 추후 분리
        StatusCode deletedCode = StatusCode.builder().id(203).type("board")
        .status("삭제").createdAt(LocalDateTime.now()).build();
        
        boardPS.deleteBoard(deletedCode);
    }
}
