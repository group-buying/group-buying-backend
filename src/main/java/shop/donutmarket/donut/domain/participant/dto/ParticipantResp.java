package shop.donutmarket.donut.domain.participant.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import shop.donutmarket.donut.domain.board.model.Event;
import shop.donutmarket.donut.domain.participant.model.Participant;
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
        private LocalDateTime createdAt;

        @Builder
        public ParticipantListRespDTO(Event event, User user, int qty, LocalDateTime limitTime,
                LocalDateTime createdAt) {
            this.event = event;
            this.user = user;
            this.qty = qty;
            this.limitTime = limitTime;
            this.createdAt = createdAt;
        }
    }

    @Getter
    @Setter
    public static class ParticipantSaveRespDTO {
        private Participant participant;

        public ParticipantSaveRespDTO(Participant participant) {
            this.participant = participant;
        }
    }

    
    @Getter
    @Setter
    @AllArgsConstructor
    public static class ParticipantSelectRespDTO {
        private Long id;
        private Event event;
        private User user;
        private String statusMsg;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class ParticipantCancleRespDTO {
        private Long id;
        private Event event;
        private User user;
        private String statusMsg;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class ParticipantDropRespDTO {
        private Long id;
        private Event event;
        private User user;
        private String statusMsg;
    }

}
