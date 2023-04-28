package shop.donutmarket.donut.integration.account;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import shop.donutmarket.donut.core.MyRestDocs;
import shop.donutmarket.donut.domain.account.dto.AccountReq;
import shop.donutmarket.donut.domain.user.repository.UserRepository;
import shop.donutmarket.donut.global.dummy.DummyEntity;

@DisplayName("계좌 API")
@AutoConfigureRestDocs(uriScheme = "http", uriHost = "localhost", uriPort = 8080)
@ActiveProfiles("test")
@Sql("classpath:db/teardown.sql")
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class AccountControllerTest extends MyRestDocs {

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
        userRepository.save(dummy.newUser("ssar", "쌀"));
        userRepository.save(dummy.newUser("cos", "코스"));
        em.clear();
    }

    @DisplayName("계좌  등록")
    @Test
    public void insert_test() throws Exception {
        // given
        AccountReq.insertDTO insertDTO = new AccountReq.insertDTO();
        insertDTO.
        AccountReq.JoinInDTO joinInDTO = new UserRequest.JoinInDTO();
        joinInDTO.setUsername("love");
        joinInDTO.setPassword("1234");
        joinInDTO.setEmail("love@nate.com");
        joinInDTO.setFullName("러브");
        String requestBody = om.writeValueAsString(joinInDTO);

        // when
        ResultActions resultActions = mvc
                .perform(post("/join").content(requestBody).contentType(MediaType.APPLICATION_JSON));
        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        System.out.println("테스트 : " + responseBody);

        // then
        resultActions.andExpect(jsonPath("$.data.id").value(3L));
        resultActions.andExpect(jsonPath("$.data.username").value("love"));
        resultActions.andExpect(jsonPath("$.data.fullName").value("러브"));
        resultActions.andExpect(status().isOk());
        resultActions.andDo(MockMvcResultHandlers.print()).andDo(document);
    }
}
