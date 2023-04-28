package shop.donutmarket.donut.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import shop.donutmarket.donut.domain.board.dto.BoardReq.BoardSaveReqDTO;
import shop.donutmarket.donut.domain.board.dto.BoardReq.BoardUpdateReqDTO;
import shop.donutmarket.donut.domain.board.dto.BoardResp.BoardSaveRespDTO;
import shop.donutmarket.donut.domain.board.dto.BoardResp.BoardUpdateRespDTO;
import shop.donutmarket.donut.domain.board.model.Board;
import shop.donutmarket.donut.domain.board.model.Event;
import shop.donutmarket.donut.domain.board.repository.BoardRepository;
import shop.donutmarket.donut.domain.board.repository.EventRepository;
import shop.donutmarket.donut.domain.board.repository.TagRepository;
import shop.donutmarket.donut.domain.board.service.BoardService;
import shop.donutmarket.donut.domain.user.model.User;

@ExtendWith(MockitoExtension.class)
public class BoardServiceTest {

//    @InjectMocks
//    private BoardService boardService;
//
//    @Mock
//    private BoardRepository boardRepository;
//
//    @Mock
//    private EventRepository eventRepository;
//
//    @Mock
//    private TagRepository tagRepository;
//
//    @Spy
//    ObjectMapper om;
//
//    @Test
//    @DisplayName("공고작성 테스트")
//    public void 공고작성_test() throws Exception {
//        // given
//        User user1 = User.builder().id(1L).username("ssar").password("1234")
//        .email("ssar@ssar").name("ssar").rate(new RateConst()).role("user")
//        .statusCode(new StatusCodeConst()).createdAt(LocalDateTime.now()).build();
//
//        List<String> comment = new ArrayList<String>();
//        comment.add("편의점");
//        comment.add("2+1");
//
//        BoardSaveReqDTO boardSaveReqDTO = new BoardSaveReqDTO(
//            new CategoryConst(),"제목1",user1,"내용1","img1",null,
//            "부산시","부산진구","부전동",139.123123,39.123123,
//            3,"직거래",LocalDateTime.now(),1000,comment);
//
//        Event event = boardSaveReqDTO.toEventEntity();
//        Board board = boardSaveReqDTO.toBoardEntity(event, boardSaveReqDTO.getImg());
//
//        // stub
//        when(eventRepository.save(any())).thenReturn(event);
//        when(boardRepository.save(any())).thenReturn(board);
//
//        // when
//        BoardSaveRespDTO boardSaveRespDTO = boardService.게시글작성(boardSaveReqDTO, user1);
//        om.registerModule(new JavaTimeModule());
//        String responseBody = om.writeValueAsString(boardSaveRespDTO);
//        System.out.println("Test : " + responseBody);
//
//        // then
//        assertThat(boardSaveRespDTO.getBoard().getTitle()).isEqualTo("제목1");
//    }
//
//    @Test
//    @DisplayName("상세보기 테스트")
//    public void 상세보기_test() throws Exception {
//        // given
//        Long id = 1L;
//
//        Board tempBoard = Board.builder().id(id).category(new CategoryConst()).title("제목")
//        .organizer(new UserConst()).content("내용1").event(new EventConst())
//        .statusCode(new StatusCodeConst()).state("부산").city("부산진")
//        .town("부전").createdAt(LocalDateTime.now()).build();
//
//        // stub
//        when(boardRepository.findById(1L)).thenReturn(Optional.of(tempBoard));
//
//        // when
//        Board boardPS = boardService.게시글상세보기(id);
//
//        // then
//        assertThat(boardPS.getTitle()).isEqualTo("제목");
//        assertThat(boardPS.getCity()).isEqualTo("부산진");
//
//    }
//
//    @Test
//    @DisplayName("업데이트 테스트")
//    public void 업데이트_test() throws Exception {
//        // given
//        List<String> comment = new ArrayList<String>();
//        comment.add("편의점");
//        comment.add("1+1");
//
//        BoardSaveReqDTO boardSaveReqDTO = new BoardSaveReqDTO(
//            new CategoryConst(),"제목1", new UserConst(),"내용1","img1",null,
//            "부산시","부산진구","부전동",139.123123,39.123123,
//            3,"직거래",LocalDateTime.now(),1000,comment);
//
//        BoardUpdateReqDTO boardUpdateReqDTO = new BoardUpdateReqDTO(1L, new CategoryConst(),"제목업데이트","내용업데이트","img1",null,
//        "부산시","해운대구","우동",139.123123,39.123123,
//        3,"직거래",LocalDateTime.now(),LocalDateTime.now(),1000,comment);
//
//        Event event = boardSaveReqDTO.toEventEntity();
//        Board board = boardSaveReqDTO.toBoardEntity(event, "img");
//
//        // stub
//        when(eventRepository.save(event)).thenReturn(event);
//        when(boardRepository.save(board)).thenReturn(board);
//        when(boardRepository.findById(any())).thenReturn(Optional.of(board));
//
//        eventRepository.save(event);
//        boardRepository.save(board);
//
//        // when
//        BoardUpdateRespDTO boardUpdateRespDTO = boardService.게시글수정(boardUpdateReqDTO);
//        om.registerModule(new JavaTimeModule());
//        String responseBody = om.writeValueAsString(boardUpdateRespDTO);
//        System.out.println("Test : " + responseBody);
//
//        // then
//        assertThat(boardUpdateReqDTO.getTitle()).isEqualTo(boardUpdateRespDTO.getBoard().getTitle());
//        assertThat(boardUpdateReqDTO.getCity()).isEqualTo(boardUpdateRespDTO.getBoard().getCity());
//
//    }
}
