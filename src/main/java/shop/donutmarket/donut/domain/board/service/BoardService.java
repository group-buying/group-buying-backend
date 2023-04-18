package shop.donutmarket.donut.domain.board.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import shop.donutmarket.donut.domain.board.repository.BoardRepository;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

}
