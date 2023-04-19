package shop.donutmarket.donut.domain.board.controller;

import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import shop.donutmarket.donut.domain.board.service.BoardService;

@RestController
@RequiredArgsConstructor
public class BoardController {
    
    private final BoardService boardService;
    
}
