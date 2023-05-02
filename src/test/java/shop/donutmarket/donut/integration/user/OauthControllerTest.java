package shop.donutmarket.donut.integration.user;

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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import shop.donutmarket.donut.core.MyRestDocs;
import shop.donutmarket.donut.domain.user.dto.UserReq;
import shop.donutmarket.donut.domain.user.model.User;
import shop.donutmarket.donut.domain.user.repository.UserRepository;
import shop.donutmarket.donut.global.dummy.DummyEntity;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("네이버 로그인 API")
@AutoConfigureRestDocs(uriScheme = "http", uriHost = "localhost", uriPort = 8080)
@ActiveProfiles("test")
@Sql("classpath:db/teardown.sql")
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class OauthControllerTest extends MyRestDocs {
    private DummyEntity dummy = new DummyEntity();

    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper om;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EntityManager em;

    @BeforeEach
    public void setUp() {

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        // Oauth 유저 객체
        User user1 = User.builder()
                .username("naver_1")
                .provider("naver")
                .providerId("1")
                .password(passwordEncoder.encode("1234"))
                .name("쌀")
                .email("ssar@naver.com")
                .role("ROLE_USER")
                .profile("사진")
                .ratePoint(20)
                .createdAt(LocalDateTime.now())
                .build();

        userRepository.save(user1);

        em.clear();
    }

    @AfterEach
    void clean() {
//        userRepository.deleteAll();
    }

    @DisplayName("회원가입 안 되어있을 경우")
    @Test
    public void login1_test() throws Exception {
        // given
        String accessToken = "";

        // when
        ResultActions resultActions = mvc
                .perform(post("/oauth/naver").header("Authorization", accessToken));

        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        System.out.println("테스트 : " + responseBody);

        // then
        resultActions.andExpect(status().isOk());
        resultActions.andExpect(header().exists("Authorization"));
        resultActions.andDo(MockMvcResultHandlers.print()).andDo(document);
    }

    @DisplayName("회원가입 되어있을 경우")
    @Test
    public void login2_test() throws Exception {
        // given
        String accessToken = "";

        // when
        ResultActions resultActions = mvc
                .perform(post("/oauth/naver").header("Authorization", accessToken));

        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        System.out.println("테스트 : " + responseBody);

        // then
        resultActions.andExpect(status().isOk());
        resultActions.andExpect(header().exists("Authorization"));
        resultActions.andDo(MockMvcResultHandlers.print()).andDo(document);
    }
}
