package shop.donutmarket.donut.domain.board;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fasterxml.jackson.databind.ObjectMapper;

import shop.donutmarket.donut.domain.board.dto.BoardReq.BoardSaveReqDTO;
import shop.donutmarket.donut.domain.board.repository.BoardRepository;
import shop.donutmarket.donut.domain.board.repository.EventRepository;
import shop.donutmarket.donut.domain.board.repository.TagRepository;
import shop.donutmarket.donut.domain.board.service.BoardService;
import shop.donutmarket.donut.domain.mycategory.CategoryConst;
import shop.donutmarket.donut.domain.review.RateConst;
import shop.donutmarket.donut.domain.user.StatusCodeConst;
import shop.donutmarket.donut.domain.user.model.User;

@ExtendWith(MockitoExtension.class)
public class BoardServiceTest {

    @InjectMocks
    private BoardService boardService;
    
    @Mock
    private BoardRepository boardRepository;
    
    @Mock
    private EventRepository eventRepository; 
    
    @Mock
    private TagRepository tagRepository;

    @Spy
    private ObjectMapper om;

}
