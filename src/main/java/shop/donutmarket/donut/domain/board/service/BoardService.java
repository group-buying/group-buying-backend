package shop.donutmarket.donut.domain.board.service;

import java.io.IOException;
import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import shop.donutmarket.donut.domain.board.dto.BoardReq.BoardSaveReqDTO;
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
    public void 공고작성(BoardSaveReqDTO boardSaveReqDTO, User user) {

        // event 먼저 save
        Event event = Event.builder().latitude(boardSaveReqDTO.getLatitude())
        .longtitude(boardSaveReqDTO.getLongtitude()).qty(boardSaveReqDTO.getQty())
        .paymentType(boardSaveReqDTO.getPaymentType()).startAt(LocalDateTime.now())
        .endAt(boardSaveReqDTO.getEndAt()).price(boardSaveReqDTO.getPrice()).build();
        
        event = eventRepository.save(event);
        
        // image base64화
        String image = null;
        try {
            image = MyBase64Decoder.saveImage(boardSaveReqDTO.getImg());
        } catch (IOException e) {
            // Exception 처리 필요
        }

        // board save
        Board board = Board.builder().category(boardSaveReqDTO.getCategory())
        .title(boardSaveReqDTO.getTitle()).organizer(user).event(event).img(image)
        .content(boardSaveReqDTO.getContent()).statusCode(boardSaveReqDTO.getStatusCode())
        .state(boardSaveReqDTO.getState()).city(boardSaveReqDTO.getCity())
        .town(boardSaveReqDTO.getTown()).createdAt(LocalDateTime.now()).build();

        board = boardRepository.save(board);

        // tag save
        Tag tag = Tag.builder().board(board).comment(boardSaveReqDTO.getComment())
        .createdAt(LocalDateTime.now()).build();

        tagRepository.save(tag);
    }

    public Board 상세보기(Long id) {

        Board boardPS = boardRepository.findById(id).get();
        return boardPS;
    }

}
