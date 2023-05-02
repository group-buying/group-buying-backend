package shop.donutmarket.donut.integration.main;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
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
import shop.donutmarket.donut.domain.blacklist.repository.BlackListRepository;
import shop.donutmarket.donut.domain.main.dto.MainResp;
import shop.donutmarket.donut.domain.myLocation.repository.MyLocationRepository;
import shop.donutmarket.donut.domain.review.model.Rate;
import shop.donutmarket.donut.domain.review.repository.RateRepository;
import shop.donutmarket.donut.domain.user.repository.UserRepository;
import shop.donutmarket.donut.global.dummy.DummyEntity;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("메인 API")
@AutoConfigureRestDocs(uriScheme = "http", uriHost = "localhost", uriPort = 8080)
@ActiveProfiles("test")
@Sql("classpath:db/teardown.sql")
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class MainControllerTest extends MyRestDocs {
    private DummyEntity dummy = new DummyEntity();

    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper om;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MyLocationRepository myLocationRepository;
    @Autowired
    private RateRepository rateRepository;
    @Autowired
    private EntityManager em;

    @BeforeEach
    public void setUp() {
        Rate rate = Rate.builder().rateName("글레이즈드").createdAt(LocalDateTime.now()).build();
        rateRepository.save(rate);
        userRepository.save(dummy.newUser("ssar@naver.com", "쌀", rate));
        userRepository.save(dummy.newUser("cos@naver.com", "쌀", rate));

        em.clear();
    }

    @DisplayName("게시글 목록 보기")
    @WithUserDetails(value = "ssar@naver.com", setupBefore = TestExecutionEvent.TEST_EXECUTION)
    @Test
    public void select_test() throws Exception {
        // given

        // when
        ResultActions resultActions = mvc
                .perform(get("/main"));

        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        System.out.println("테스트 : " + responseBody);

        // then
//        resultActions.andExpect(jsonPath("$.blacklist.user.username").value("ssar@naver.com"));
//        resultActions.andExpect(jsonPath("$.blacklist.blockedUser.username").value("cos@naver.com"));
//        resultActions.andExpect(status().isOk());
//        resultActions.andDo(MockMvcResultHandlers.print()).andDo(document);
    }
}
