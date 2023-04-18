package shop.donutmarket.donut.domain.board;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;
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
import shop.donutmarket.donut.domain.mycategory.CategoryConst;
import shop.donutmarket.donut.domain.user.StatusCodeConst;
import shop.donutmarket.donut.domain.user.UserConst;

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
        Board board = Board.builder().id(1L).category(new CategoryConst()).title("삼각김밥 1+1 사실분").organizer(new UserConst()).event(new EventConst()).content("서면역 1번출구에서").build();
        boardRepository.save(board);
    }
    
    // @Test
    // void findAll_Test(){
    //     // given

    //     // when
    //     List<Board> boards = boardRepository.findAll();
        
    //     // then
    //     assertEquals(boards.size(), 1);
    // }

    // findAll은 Lazy Loading이 아닌 Eager Loading 이기에 모든 연관 엔티티를 미리 로딩해야하므로 테스트가 어려움

    @Test
    void findById_Test() {
        // given
        Long id = 1L;
        // when
        Optional<Board> optionalboard = boardRepository.findById(id);
        // then
        assertNotNull(optionalboard);
    }
    
    @Test
    void save_Test(){
        // given
        Long id = 2L;
        Board board = Board.builder().id(id).category(new CategoryConst()).title("도시락 1+1 사실분").organizer(new UserConst()).event(new EventConst()).content("서면역 2번출구에서").build();
        
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
        Long id = 1L;
        Tag tag = Tag.builder().id(id).board(new BoardConst()).comment("편의점").build();
        
        // when
        tagRepository.save(tag);
        
        // then
        assertNotNull(boardRepository.findById(id));
    }
    
    
    @Test
    void tag_DeleteById_Test(){
        // given
        Long id = 1L;
        Tag tag = Tag.builder().id(id).board(new BoardConst()).comment("편의점").build();
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
        Event event = Event.builder().id(id).latitude(139.123123).longtitude(39.123123).qty(2).paymentType("직거래").startAt(time).endAt(time).statusCode(new StatusCodeConst()).price(1000).build();
        
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
        Event event = Event.builder().id(id).latitude(139.123123).longtitude(39.123123).qty(2).paymentType("직거래").startAt(time).endAt(time).statusCode(new StatusCodeConst()).price(1000).build();
        eventRepository.save(event);
        
        // when
        eventRepository.deleteById(id);

        // then
        assertNotNull(eventRepository.findById(id));
        
    }

}
