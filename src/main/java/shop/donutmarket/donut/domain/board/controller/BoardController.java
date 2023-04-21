package shop.donutmarket.donut.domain.board.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import shop.donutmarket.donut.domain.board.dto.BoardReq.BoardDeleteReqDTO;
import shop.donutmarket.donut.domain.board.dto.BoardReq.BoardSaveReqDTO;
import shop.donutmarket.donut.domain.board.dto.BoardReq.BoardUpdateReqDTO;
import shop.donutmarket.donut.domain.board.dto.BoardResp.BoardDetailRespDTO;
import shop.donutmarket.donut.domain.board.dto.BoardResp.BoardSaveRespDTO;
import shop.donutmarket.donut.domain.board.dto.BoardResp.BoardUpdateRespDTO;
import shop.donutmarket.donut.domain.board.model.Board;
import shop.donutmarket.donut.domain.board.model.Tag;
import shop.donutmarket.donut.domain.board.service.BoardService;
import shop.donutmarket.donut.domain.board.service.TagService;
import shop.donutmarket.donut.domain.user.model.User;
import shop.donutmarket.donut.global.auth.MyUserDetails;
import shop.donutmarket.donut.global.dto.ResponseDTO;



@RestController
@RequiredArgsConstructor
@RequestMapping("/boards")
public class BoardController {
    
    private final BoardService boardService;
    private final TagService tagService;
    private final HttpSession session;

    @PostMapping
    public ResponseEntity<?> save(@AuthenticationPrincipal MyUserDetails myUserDetails, @RequestBody BoardSaveReqDTO boardSaveReqDTO, BindingResult bindingResult) {
        
        if(myUserDetails == null) {
            // 예외 처리
        }

        User user = (User) session.getAttribute("principal");

        BoardSaveRespDTO saveRespDTO = boardService.공고작성(boardSaveReqDTO, myUserDetails);
        return new ResponseEntity<>(new ResponseDTO<>().data(saveRespDTO), HttpStatus.CREATED);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> detail(@PathVariable Long id) {

        Board board = boardService.상세보기(id);
        List<String> tags = tagService.상세보기(id);

        BoardDetailRespDTO detailRespDTO = new BoardDetailRespDTO(board, tags);

        // tag도 같이 줘야함
        return new ResponseEntity<>(new ResponseDTO<>().data(detailRespDTO), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> update(@AuthenticationPrincipal MyUserDetails myUserDetails, @RequestBody @Valid BoardUpdateReqDTO boardUpdateReqDTO) {

        if(myUserDetails == null) {
            // 예외 처리
        }

        BoardUpdateRespDTO updateRespDTO = boardService.업데이트(boardUpdateReqDTO, myUserDetails);

        return new ResponseEntity<>(new ResponseDTO<>().data(updateRespDTO), HttpStatus.CREATED);
    }

    @PutMapping("/delete")
    public ResponseEntity<?> delete(@AuthenticationPrincipal MyUserDetails myUserDetails, @RequestBody @Valid BoardDeleteReqDTO boardDeleteReqDTO){

        if(myUserDetails == null) {
            // 예외 처리
        }

        boardService.삭제(boardDeleteReqDTO, myUserDetails);

        return new ResponseEntity<>(new ResponseDTO<>(), HttpStatus.OK); 
    }
}
