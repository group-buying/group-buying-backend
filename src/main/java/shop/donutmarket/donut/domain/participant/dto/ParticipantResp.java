package shop.donutmarket.donut.domain.participant.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import shop.donutmarket.donut.domain.admin.model.StatusCode;
import shop.donutmarket.donut.domain.board.model.Event;
import shop.donutmarket.donut.domain.user.model.User;

public class ParticipantResp {
    
    @Getter
    @Setter
    @NoArgsConstructor
    public static class ParticipantListRespDTO {
        private Event event;
        private User user;
        private int qty;
        private LocalDateTime limitTime;
        private StatusCode statusCode;
        private LocalDateTime createdAt;

        @Builder
        public ParticipantListRespDTO(Event event, User user, int qty, LocalDateTime limitTime, StatusCode statusCode,
                LocalDateTime createdAt) {
            this.event = event;
            this.user = user;
            this.qty = qty;
            this.limitTime = limitTime;
            this.statusCode = statusCode;
            this.createdAt = createdAt;
        }
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class ParticipantSaveRespDTO {
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

    @Getter
    @Setter
    @AllArgsConstructor
    public static class ParticipantDropRespDTO {
        private Long id;
        private Event event;
        private User user;
        private StatusCode statusCode;
    }

}