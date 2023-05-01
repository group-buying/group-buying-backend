package shop.donutmarket.donut.integration.participant;

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
import shop.donutmarket.donut.core.MyRestDocs;
import shop.donutmarket.donut.domain.board.model.Event;
import shop.donutmarket.donut.domain.board.repository.EventRepository;
import shop.donutmarket.donut.domain.participant.dto.ParticipantReq;
import shop.donutmarket.donut.domain.user.repository.UserRepository;
import shop.donutmarket.donut.global.dummy.DummyEntity;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("참가 API")
@AutoConfigureRestDocs(uriScheme = "http", uriHost = "localhost", uriPort = 8080)
@ActiveProfiles("test")
@Sql("classpath:db/teardown.sql")
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class ParticipantControllerTest extends MyRestDocs {

    private DummyEntity dummy = new DummyEntity();

    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper om;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private EntityManager em;

    @BeforeEach
    public void setUp() {
        userRepository.save(dummy.newUser("ssar@naver.com", "쌀"));
        userRepository.save(dummy.newUser("cos@naver.com", "쌀"));

        // 계좌 dummy 생성

        // 이벤트 객체
        Event event = Event.builder().latitude(111.111).longtitude(222.222).qty(1).paymentType("직거래")
                .startAt(LocalDateTime.of(2023, 5, 1, 13, 30))
                .endAt(LocalDateTime.of(2023, 5, 2, 13, 30))
                .price(1000).createdAt(LocalDateTime.now()).build();
        eventRepository.save(event);

        em.clear();
    }

    @AfterEach
    void clean() {}


    @DisplayName("참가하기")
    @WithUserDetails(value = "ssar@naver.com", setupBefore = TestExecutionEvent.TEST_EXECUTION)
    @Test
    public void save_test() throws Exception {
        // given
        Long eventId = 1L;
        Long userId = 1L;
        Long statusCodeId = 300L;

        ParticipantReq.ParticipantSaveReqDTO participantSaveReqDTO =
                new ParticipantReq.ParticipantSaveReqDTO();
        participantSaveReqDTO.setEventId(eventId);
        participantSaveReqDTO.setUserId(userId);
        participantSaveReqDTO.setStatusCodeId(statusCodeId);
        participantSaveReqDTO.setQty(10);
        participantSaveReqDTO.setLimitTime(LocalDateTime.of(2023, 5, 1, 13, 30));

        String requestBody = om.writeValueAsString(participantSaveReqDTO);

        // when
        ResultActions resultActions = mvc
                .perform(post("/participants").content(requestBody).contentType(MediaType.APPLICATION_JSON));

        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        System.out.println("테스트 : " + responseBody);

        // then
        resultActions.andExpect(jsonPath("$.participant.event.paymentType").value("직거래"));
        resultActions.andExpect(jsonPath("$.participant.user.username").value("ssar@naver.com"));
        resultActions.andExpect(status().isOk());
        resultActions.andDo(MockMvcResultHandlers.print()).andDo(document);
    }

    @DisplayName("채택하기")
    @WithUserDetails(value = "ssar@naver.com", setupBefore = TestExecutionEvent.TEST_EXECUTION)
    @Test
    public void select_test() throws Exception {
        // given
        Long eventId = 1L;
        Long userId = 1L;
        Long statusCodeId = 300L;

        ParticipantReq.ParticipantSaveReqDTO participantSaveReqDTO =
                new ParticipantReq.ParticipantSaveReqDTO();
        participantSaveReqDTO.setEventId(eventId);
        participantSaveReqDTO.setUserId(userId);
        participantSaveReqDTO.setStatusCodeId(statusCodeId);
        participantSaveReqDTO.setQty(10);
        participantSaveReqDTO.setLimitTime(LocalDateTime.of(2023, 5, 1, 13, 30));

        String requestBody = om.writeValueAsString(participantSaveReqDTO);

        // when
        ResultActions resultActions = mvc
                .perform(post("/participants").content(requestBody).contentType(MediaType.APPLICATION_JSON));

        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        System.out.println("테스트 : " + responseBody);

        // then
        resultActions.andExpect(jsonPath("$.participant.event.paymentType").value("직거래"));
        resultActions.andExpect(jsonPath("$.participant.user.username").value("ssar@naver.com"));
        resultActions.andExpect(status().isOk());
        resultActions.andDo(MockMvcResultHandlers.print()).andDo(document);
    }
}
