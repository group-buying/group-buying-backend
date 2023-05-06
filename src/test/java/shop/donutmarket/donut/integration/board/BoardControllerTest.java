package shop.donutmarket.donut.integration.board;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.TestExecutionEvent;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.transaction.annotation.Transactional;
import shop.donutmarket.donut.core.MyRestDocs;
import shop.donutmarket.donut.domain.admin.model.Category;
import shop.donutmarket.donut.domain.admin.repository.CategoryRepository;
import shop.donutmarket.donut.domain.blacklist.repository.BlackListRepository;
import shop.donutmarket.donut.domain.board.dto.BoardReq;
import shop.donutmarket.donut.domain.board.repository.EventRepository;
import shop.donutmarket.donut.domain.review.model.Rate;
import shop.donutmarket.donut.domain.review.repository.RateRepository;
import shop.donutmarket.donut.domain.user.model.User;
import shop.donutmarket.donut.domain.user.repository.UserRepository;
import shop.donutmarket.donut.global.dummy.DummyEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("게시글 API")
@AutoConfigureRestDocs(uriScheme = "http", uriHost = "localhost", uriPort = 8080)
@ActiveProfiles("test")
@Sql("classpath:db/teardown.sql")
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class BoardControllerTest extends MyRestDocs {
    private DummyEntity dummy = new DummyEntity();

    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper om;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RateRepository rateRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private EntityManager em;

    @BeforeEach
    public void setUp() {
        categoryRepository.deleteAll();
        eventRepository.deleteAll();

        Rate rate = Rate.builder().rateName("글레이즈드").createdAt(LocalDateTime.now()).build();
        rateRepository.save(rate);
        User user1 = userRepository.save(dummy.newUser("ssar@naver.com", "쌀", rate));
        userRepository.save(dummy.newUser("cos@naver.com", "쌀", rate));

        // 카테고리 객체
        Category category = Category.builder().name("편의점").createdAt(LocalDateTime.now()).build();
        categoryRepository.save(category);

        em.clear();
    }

    @AfterEach
    void clean() {
    }

    @DisplayName("게시글 작성하기")
    @WithUserDetails(value = "ssar@naver.com", setupBefore = TestExecutionEvent.TEST_EXECUTION)
    @Test
    public void save_test() throws Exception {
        // given
        BoardReq.BoardSaveReqDTO boardSaveReqDTO = new BoardReq.BoardSaveReqDTO();
        boardSaveReqDTO.setCategoryId(1L);
        boardSaveReqDTO.setTitle("제목");
        boardSaveReqDTO.setContent("내용");
        boardSaveReqDTO.setState("부산광역시");
        boardSaveReqDTO.setCity("부산진구");
        boardSaveReqDTO.setTown("부전동");
        boardSaveReqDTO.setLatitude(111.222);
        boardSaveReqDTO.setLongtitude(222.333);
        boardSaveReqDTO.setQty(3);
        boardSaveReqDTO.setPaymentType("직거래");
        boardSaveReqDTO.setEndAt(LocalDateTime.of(2023, 5, 2, 13, 30));
        boardSaveReqDTO.setPrice(1000);
        List<String> comment = new ArrayList<>();
        comment.add("댓글1");
        comment.add("댓글2");
        boardSaveReqDTO.setComment(comment);

        String requestBody = om.writeValueAsString(boardSaveReqDTO);

        // when
        ResultActions resultActions = mvc
                .perform(post("/boards").content(requestBody).contentType(MediaType.APPLICATION_JSON));

        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        System.out.println("테스트 : " + responseBody);

        // then
        resultActions.andExpect(jsonPath("$.data.board.category.name").value("편의점"));
        resultActions.andExpect(jsonPath("$.data.board.title").value("제목"));
        resultActions.andExpect(jsonPath("$.data.board.organizer.name").value("쌀"));
        resultActions.andExpect(jsonPath("$.data.board.event.paymentType").value("직거래"));
        resultActions.andExpect(status().isOk());
        resultActions.andDo(MockMvcResultHandlers.print()).andDo(document);
    }
}
