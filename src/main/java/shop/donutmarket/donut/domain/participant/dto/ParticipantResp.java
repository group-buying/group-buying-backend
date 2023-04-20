package shop.donutmarket.donut.domain.participant.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import shop.donutmarket.donut.domain.admin.model.StatusCode;
import shop.donutmarket.donut.domain.board.model.Event;
import shop.donutmarket.donut.domain.user.model.User;

public class ParticipantResp {
    
    @Getter
    @Setter
    @AllArgsConstructor
    public static class ParticipantSaveRespDTO {
        private Long id;
        private Event event;
        private User user;
        private int qty;
        private LocalDateTime limitTime;
        private StatusCode statusCode;
        private LocalDateTime createdAt;

    }

    
    @Getter
    @Setter
    @AllArgsConstructor
    public static class ParticipantSelectRespDTO {
        private Long id;
        private Event event;
        private User user;
        private StatusCode statusCode;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class ParticipantCancleRespDTO {
        private Long id;
        private Event event;
        private User user;
        private StatusCode statusCode;
    }

}
