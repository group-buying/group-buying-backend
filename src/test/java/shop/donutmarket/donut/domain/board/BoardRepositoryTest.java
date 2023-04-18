package shop.donutmarket.donut.domain.board;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import shop.donutmarket.donut.domain.board.model.Board;
import shop.donutmarket.donut.domain.board.model.Event;
import shop.donutmarket.donut.domain.board.model.Tag;
import shop.donutmarket.donut.domain.board.repository.BoardRepository;
import shop.donutmarket.donut.domain.board.repository.EventRepository;
import shop.donutmarket.donut.domain.board.repository.TagRepository;

@DataJpaTest
@Transactional
public class BoardRepositoryTest {
    
    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    EntityManager em;

    @BeforeEach
    public void setUp() {
        Board board = Board.builder().id(1L).categoryId(1L).title("삼각김밥 1+1 사실분").organizerId(1L).eventId(1L).content("서면역 1번출구에서").build();
        boardRepository.save(board);
    }
    
    @Test
    void findAll_Test(){
        // given

        // when
        List<Board> boards = boardRepository.findAll();
        
        // then
        assertEquals(boards.size(), 1);
    }

    @Test
    void findById_Test() {
        // given
        Long id = 1L;
        // when
        Optional<Board> optionalboard = boardRepository.findById(id);
        // then
        assertNotNull(optionalboard);
        assertEquals("삼각김밥 1+1 사실분", optionalboard.get().getTitle());
    }
    
    @Test
    void save_Test(){
        // given
        Long id = 2L;
        Board board = Board.builder().id(id).categoryId(1L).title("도시락 1+1 사실분").organizerId(1L).eventId(2L).content("서면역 2번출구에서").build();
        
        // when
        boardRepository.save(board);
        
        // then
        assertNotNull(boardRepository.findById(id));
    }
    
    // @Test
    // void update_Test() {
    //     // given
    //     Long id = 1L;
    //     String newTitle = "콜라 2+1 사실분";

    //     // when
    //     Board board = boardRepository.findById(id).get();
    //     board.setTitle(newTitle);

    //     em.merge(board);
    //     em.flush();
        
    //     // then
    //     Optional<Board> boardPS = boardRepository.findById(id);
    //     assertNotNull(boardPS);

    //     // System.out.println("DEBUG : " + boardPS.get().getTitle());
    //     assertEquals(newTitle, boardPS.get().getTitle()); 
    // }
    
    @Test
    void deleteById_Test(){
        // given
        Long id = 1L;

        // when
        boardRepository.deleteById(id);

        // then
        assertEquals(Optional.empty(), boardRepository.findById(id)); 
    }
    
    @Test
    void tag_Save_Test(){
        // given
        Long board_id = 1L;
        Long id = 1L;
        Tag tag = Tag.builder().id(id).boardId(board_id).comment("편의점").build();
        
        // when
        tagRepository.save(tag);
        
        // then
        assertNotNull(boardRepository.findById(id));
    }
    
    
    @Test
    void tag_DeleteById_Test(){
        // given
        Long id = 1L;
        Tag tag = Tag.builder().id(id).boardId(1L).comment("편의점").build();
        tagRepository.save(tag);
        
        // when
        tagRepository.deleteById(id);
        
        // then
        assertEquals(Optional.empty(), tagRepository.findById(id)); 
    }
    
    
    @Test
    void event_Save_Test(){
        // given
        Long id = 1L;
        LocalDateTime time = LocalDateTime.now();
        Event event = Event.builder().id(id).latitude(139.123123).longtitude(39.123123).qty(2).paymentType("직거래").startAt(time).endAt(time).statusCode(200).price(1000).build();
        
        eventRepository.save(event);
        // when
        
        // then
        assertNotNull(eventRepository.findById(id));
        
    }


    @Test
    void event_Delete_Test(){
        // given
        Long id = 1L;
        LocalDateTime time = LocalDateTime.now();
        Event event = Event.builder().id(id).latitude(139.123123).longtitude(39.123123).qty(2).paymentType("직거래").startAt(time).endAt(time).statusCode(200).price(1000).build();
        eventRepository.save(event);
        
        // when
        eventRepository.deleteById(id);

        // then
        assertNotNull(eventRepository.findById(id));
        
    }

}
