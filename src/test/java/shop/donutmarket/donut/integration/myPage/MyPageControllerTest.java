package shop.donutmarket.donut.integration.myPage;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jdk.jshell.Snippet;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.TestExecutionEvent;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import shop.donutmarket.donut.core.MyRestDocs;
import shop.donutmarket.donut.domain.admin.model.Category;
import shop.donutmarket.donut.domain.admin.model.StatusCode;
import shop.donutmarket.donut.domain.admin.repository.CategoryRepository;
import shop.donutmarket.donut.domain.admin.repository.StatusCodeRepository;
import shop.donutmarket.donut.domain.board.model.Board;
import shop.donutmarket.donut.domain.board.model.Event;
import shop.donutmarket.donut.domain.board.repository.BoardRepository;
import shop.donutmarket.donut.domain.board.repository.EventRepository;
import shop.donutmarket.donut.domain.myCategory.repository.MyCategoryRepository;
import shop.donutmarket.donut.domain.myLocation.model.MyLocation;
import shop.donutmarket.donut.domain.myLocation.repository.MyLocationRepository;
import shop.donutmarket.donut.domain.user.model.User;
import shop.donutmarket.donut.domain.user.repository.UserRepository;
import shop.donutmarket.donut.global.dummy.DummyEntity;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("마이 페이지 API")
@AutoConfigureRestDocs(uriScheme = "http", uriHost = "localhost", uriPort = 8080)
@ActiveProfiles("test")
@Sql("classpath:db/teardown.sql")
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class MyPageControllerTest extends MyRestDocs {
    private DummyEntity dummy = new DummyEntity();

    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper om;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private StatusCodeRepository statusCodeRepository;
    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private EntityManager em;

    @BeforeEach
    public void setUp() {
        User user = userRepository.save(dummy.newUser("ssar@naver.com", "쌀"));
        userRepository.save(dummy.newUser("cos@naver.com", "쌀"));

//        Optional<User> userOP = userRepository.findById(1L);
//        User userPS = userOP.get();

        // 카테고리 객체
        Category category = Category.builder().name("생활가전").createdAt(LocalDateTime.now()).build();
        categoryRepository.save(category);

        // 이벤트 객체
        Event event = Event.builder().latitude(111.111).longtitude(222.222).qty(1).paymentType("직거래")
                .startAt(LocalDateTime.of(2023, 5, 1, 13, 30))
                .endAt(LocalDateTime.of(2023, 5, 2, 13, 30))
                .price(1000).createdAt(LocalDateTime.now()).build();
        eventRepository.save(event);

        // Status Code 객체
        StatusCode statusCode = StatusCode.builder().type("board").status("진행중")
                .createdAt(LocalDateTime.now()).build();
        statusCodeRepository.save(statusCode);

        Board board = Board.builder().category(category).event(event)
                .organizer(user).title("제목1").content("내용1").img("사진").statusCode(statusCode)
                .views(100).recommend(false).state("부산광역시").city("부산진구").town("부전동").build();

        boardRepository.save(board);

        em.clear();
    }

    @AfterEach
    void clean() {
        boardRepository.deleteAll();
    }

    @DisplayName("나의 게시글 보기")
    @WithUserDetails(value = "ssar@naver.com", setupBefore = TestExecutionEvent.TEST_EXECUTION)
    @Test
    public void board_test() throws Exception {
        // given

        // when
        ResultActions resultActions = mvc
                .perform(get("/myPages/boards"));

        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        System.out.println("테스트 : " + responseBody);

        // then
//        resultActions.andExpect(jsonPath("$.state").value("부산광역시"));
//        resultActions.andExpect(jsonPath("$.city").value("부산진구"));
//        resultActions.andExpect(jsonPath("$.town").value("부전동"));
//        resultActions.andExpect(status().isOk());
//        resultActions.andDo(MockMvcResultHandlers.print()).andDo(document);
    }

//    @DisplayName("나의 구매내역 보기")
//    @WithUserDetails(value = "ssar@naver.com", setupBefore = TestExecutionEvent.TEST_EXECUTION)
//    @Test
//    public void payment_test() throws Exception {
//        // given
//
//        // when
//        ResultActions resultActions = mvc
//                .perform(post("/myPages/boards"));
//
//        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
//        System.out.println("테스트 : " + responseBody);
//
//        // then
//        resultActions.andExpect(jsonPath("$.state").value("부산광역시"));
//        resultActions.andExpect(jsonPath("$.city").value("부산진구"));
//        resultActions.andExpect(jsonPath("$.town").value("부전동"));
//        resultActions.andExpect(status().isOk());
//        resultActions.andDo(MockMvcResultHandlers.print()).andDo(document);
//    }
//
//    @DisplayName("나의 블랙리스트 보기")
//    @WithUserDetails(value = "ssar@naver.com", setupBefore = TestExecutionEvent.TEST_EXECUTION)
//    @Test
//    public void blacklist_test() throws Exception {
//        // given
//
//        // when
//        ResultActions resultActions = mvc
//                .perform(post("/myPages/boards"));
//
//        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
//        System.out.println("테스트 : " + responseBody);
//
//        // then
//        resultActions.andExpect(jsonPath("$.state").value("부산광역시"));
//        resultActions.andExpect(jsonPath("$.city").value("부산진구"));
//        resultActions.andExpect(jsonPath("$.town").value("부전동"));
//        resultActions.andExpect(status().isOk());
//        resultActions.andDo(MockMvcResultHandlers.print()).andDo(document);
//    }
//
//    @DisplayName("나의 신고내역 보기")
//    @WithUserDetails(value = "ssar@naver.com", setupBefore = TestExecutionEvent.TEST_EXECUTION)
//    @Test
//    public void report_test() throws Exception {
//        // given
//
//        // when
//        ResultActions resultActions = mvc
//                .perform(post("/myPages/boards"));
//
//        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
//        System.out.println("테스트 : " + responseBody);
//
//        // then
//        resultActions.andExpect(jsonPath("$.state").value("부산광역시"));
//        resultActions.andExpect(jsonPath("$.city").value("부산진구"));
//        resultActions.andExpect(jsonPath("$.town").value("부전동"));
//        resultActions.andExpect(status().isOk());
//        resultActions.andDo(MockMvcResultHandlers.print()).andDo(document);
//    }
//
//    @DisplayName("나의 리뷰목록 보기")
//    @WithUserDetails(value = "ssar@naver.com", setupBefore = TestExecutionEvent.TEST_EXECUTION)
//    @Test
//    public void review_test() throws Exception {
//        // given
//
//        // when
//        ResultActions resultActions = mvc
//                .perform(post("/myPages/boards"));
//
//        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
//        System.out.println("테스트 : " + responseBody);
//
//        // then
//        resultActions.andExpect(jsonPath("$.state").value("부산광역시"));
//        resultActions.andExpect(jsonPath("$.city").value("부산진구"));
//        resultActions.andExpect(jsonPath("$.town").value("부전동"));
//        resultActions.andExpect(status().isOk());
//        resultActions.andDo(MockMvcResultHandlers.print()).andDo(document);
//    }
}
