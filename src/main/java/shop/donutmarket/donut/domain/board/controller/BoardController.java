package shop.donutmarket.donut.domain.board.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import shop.donutmarket.donut.domain.board.dto.BoardReq.BoardSaveReqDTO;
import shop.donutmarket.donut.domain.board.model.Board;
import shop.donutmarket.donut.domain.board.model.Tag;
import shop.donutmarket.donut.domain.board.service.BoardService;
import shop.donutmarket.donut.domain.board.service.TagService;
import shop.donutmarket.donut.domain.user.model.User;
import shop.donutmarket.donut.global.dto.ResponseDTO;



@RestController
@RequiredArgsConstructor
public class BoardController {
    
    private final BoardService boardService;
    private final TagService tagService;
    private final HttpSession session;

    @PostMapping("/boards")
    public @ResponseBody ResponseEntity<?> save(@RequestBody BoardSaveReqDTO boardSaveReqDTO, BindingResult bindingResult) {
        
        // 임시 작성 추후 로그인 방식이 정립되면 변경
        User user = (User) session.getAttribute("principal");

        boardService.공고작성(boardSaveReqDTO, user);
        return new ResponseEntity<>(new ResponseDTO<>(), HttpStatus.valueOf(201));
    }
    
    @GetMapping("/board/{id}")
    public @ResponseBody ResponseEntity<?> detail(@PathVariable Long id) {

        Board board = boardService.상세보기(id);
        List<Tag> tags = tagService.상세보기(id);

        return new ResponseEntity<>(new ResponseDTO<>().data(board), HttpStatus.OK);
    }
}
