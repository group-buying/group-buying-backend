package shop.donutmarket.donut.integration.myPage;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.test.context.support.TestExecutionEvent;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import shop.donutmarket.donut.core.MyRestDocs;
import shop.donutmarket.donut.domain.admin.model.Category;
import shop.donutmarket.donut.domain.admin.repository.CategoryRepository;
import shop.donutmarket.donut.domain.blacklist.model.Blacklist;
import shop.donutmarket.donut.domain.blacklist.repository.BlackListRepository;
import shop.donutmarket.donut.domain.board.model.Board;
import shop.donutmarket.donut.domain.board.model.Event;
import shop.donutmarket.donut.domain.board.repository.BoardRepository;
import shop.donutmarket.donut.domain.board.repository.EventRepository;
import shop.donutmarket.donut.domain.payment.model.Payment;
import shop.donutmarket.donut.domain.payment.repository.PaymentRepository;
import shop.donutmarket.donut.domain.report.model.Report;
import shop.donutmarket.donut.domain.report.repository.ReportRepository;
import shop.donutmarket.donut.domain.review.model.Rate;
import shop.donutmarket.donut.domain.review.model.Review;
import shop.donutmarket.donut.domain.review.repository.RateRepository;
import shop.donutmarket.donut.domain.review.repository.ReviewRepository;
import shop.donutmarket.donut.domain.user.model.User;
import shop.donutmarket.donut.domain.user.repository.UserRepository;
import shop.donutmarket.donut.global.dummy.DummyEntity;

import java.time.LocalDateTime;

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
    private BoardRepository boardRepository;
    @Autowired
    private BlackListRepository blackListRepository;
    @Autowired
    private RateRepository rateRepository;
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private ReportRepository reportRepository;
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private EntityManager em;

    @BeforeEach
    public void setUp() {
        // 등급 객체
        Rate rate = Rate.builder().rateName("글레이즈드").createdAt(LocalDateTime.now()).build();
        rateRepository.save(rate);

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        // rate 객체 넣기 위함
        User user1 = User.builder()
                .username("ssar@naver.com")
                .password(passwordEncoder.encode("1234"))
                .name("쌀")
                .email("ssar@naver.com")
                .role("ROLE_USER")
                .profile("사진")
                .rate(rate)
                .ratePoint(20)
                .createdAt(LocalDateTime.now())
                .build();

        userRepository.save(user1);

        User user2 = userRepository.save(dummy.newUser("cos@naver.com", "쌀"));

        // 카테고리 객체
        Category category = Category.builder().name("생활가전").createdAt(LocalDateTime.now()).build();
        categoryRepository.save(category);

        // 이벤트 객체
        Event event = Event.builder().latitude(111.111).longtitude(222.222).qty(1).paymentType("직거래")
                .startAt(LocalDateTime.of(2023, 5, 1, 13, 30))
                .endAt(LocalDateTime.of(2023, 5, 2, 13, 30))
                .price(1000).createdAt(LocalDateTime.now()).build();
        eventRepository.save(event);

        // 게시글 객체
        Board board = Board.builder().category(category).event(event)
                .organizer(user1).title("제목1").content("내용1").img("사진").statusCode(200)
                .views(100).recommend(false).state("부산광역시").city("부산진구").town("부전동").build();
        boardRepository.save(board);

        // 블랙리스트 객체
        Blacklist blacklist = Blacklist.builder().blockedUser(user2).user(user1).build();
        blackListRepository.save(blacklist);

        // 리뷰 객체
        Review review = Review.builder().reviewed(user1).reviewer(user2).score(5).comment("굿").build();
        reviewRepository.save(review);

        // 신고 객체
        Report report = Report.builder().reported(user1).reporter(user2).board(board).title("제목")
                        .content("내용").reportType("욕설").createdAt(LocalDateTime.now()).build();
        reportRepository.save(report);

        // 결제 객체
        Payment payment = Payment.builder().user(user2).event(event).paymentType("직거래").confirmed(false)
                .createdAt(LocalDateTime.now()).build();
        paymentRepository.save(payment);

        em.clear();
    }

    @AfterEach
    void clean() {}

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
        resultActions.andExpect(jsonPath("$.board[0].category.name").value("생활가전"));
        resultActions.andExpect(jsonPath("$.board[0].organizer.username").value("ssar@naver.com"));
        resultActions.andExpect(status().isOk());
        resultActions.andDo(MockMvcResultHandlers.print()).andDo(document);
    }

    @DisplayName("나의 구매내역 보기")
    @WithUserDetails(value = "cos@naver.com", setupBefore = TestExecutionEvent.TEST_EXECUTION)
    @Test
    public void payment_test() throws Exception {
        // given

        // when
        ResultActions resultActions = mvc
                .perform(get("/myPages/payments"));

        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        System.out.println("테스트 : " + responseBody);

        // then
        resultActions.andExpect(jsonPath("$.payment[0].user.username").value("cos@naver.com"));
        resultActions.andExpect(jsonPath("$.payment[0].event.paymentType").value("직거래"));
        resultActions.andExpect(jsonPath("$.payment[0].confirmed").value(false));
        resultActions.andExpect(status().isOk());
        resultActions.andDo(MockMvcResultHandlers.print()).andDo(document);
    }

    @DisplayName("나의 블랙리스트 보기")
    @WithUserDetails(value = "ssar@naver.com", setupBefore = TestExecutionEvent.TEST_EXECUTION)
    @Test
    public void blacklist_test() throws Exception {
        // given

        // when
        ResultActions resultActions = mvc
                .perform(get("/myPages/blacklists"));

        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        System.out.println("테스트 : " + responseBody);

        // then
        resultActions.andExpect(jsonPath("$.blacklist[0].user.username").value("ssar@naver.com"));
        resultActions.andExpect(jsonPath("$.blacklist[0].blockedUser.username").value("cos@naver.com"));
        resultActions.andExpect(status().isOk());
        resultActions.andDo(MockMvcResultHandlers.print()).andDo(document);
    }
//
    @DisplayName("나의 신고내역 보기")
    @WithUserDetails(value = "cos@naver.com", setupBefore = TestExecutionEvent.TEST_EXECUTION)
    @Test
    public void report_test() throws Exception {
        // given

        // when
        ResultActions resultActions = mvc
                .perform(get("/myPages/reports"));

        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        System.out.println("테스트 : " + responseBody);

        // then
        resultActions.andExpect(jsonPath("$.report[0].reporter.username").value("cos@naver.com"));
        resultActions.andExpect(jsonPath("$.report[0].reported.username").value("ssar@naver.com"));
        resultActions.andExpect(jsonPath("$.report[0].title").value("제목"));
        resultActions.andExpect(jsonPath("$.report[0].content").value("내용"));
        resultActions.andExpect(jsonPath("$.report[0].reportType").value("욕설"));
        resultActions.andExpect(status().isOk());
        resultActions.andDo(MockMvcResultHandlers.print()).andDo(document);
    }

    @DisplayName("나의 리뷰목록 보기")
    @WithUserDetails(value = "cos@naver.com", setupBefore = TestExecutionEvent.TEST_EXECUTION)
    @Test
    public void review_test() throws Exception {
        // given

        // when
        ResultActions resultActions = mvc
                .perform(get("/myPages/reviews"));

        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        System.out.println("테스트 : " + responseBody);

        // then
        resultActions.andExpect(jsonPath("$.review[0].reviewer.username").value("cos@naver.com"));
        resultActions.andExpect(jsonPath("$.review[0].reviewed.username").value("ssar@naver.com"));
        resultActions.andExpect(jsonPath("$.review[0].score").value(5));
        resultActions.andExpect(jsonPath("$.review[0].comment").value("굿"));
        resultActions.andExpect(status().isOk());
        resultActions.andDo(MockMvcResultHandlers.print()).andDo(document);
    }
}
