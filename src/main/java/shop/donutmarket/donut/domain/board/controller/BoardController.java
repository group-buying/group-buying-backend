package shop.donutmarket.donut.domain.board.controller;

import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import shop.donutmarket.donut.domain.board.dto.BoardReq.BoardSaveReqDTO;
import shop.donutmarket.donut.domain.board.service.BoardService;
import shop.donutmarket.donut.domain.user.model.User;
import shop.donutmarket.donut.global.dto.ResponseDTO;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequiredArgsConstructor
public class BoardController {
    
    private final BoardService boardService;

    @PostMapping("/boards")
    public @ResponseBody ResponseEntity<?> save(@RequestBody BoardSaveReqDTO boardSaveReqDTO, BindingResult bindingResult) {
        
        boardService.공고작성(boardSaveReqDTO, user);
        return new ResponseEntity<>(new ResponseDTO<>(), HttpStatus.valueOf(201));
    }
    
}
