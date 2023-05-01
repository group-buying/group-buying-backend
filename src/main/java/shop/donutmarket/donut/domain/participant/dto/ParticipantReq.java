package shop.donutmarket.donut.domain.participant.dto;

import java.time.LocalDateTime;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import shop.donutmarket.donut.domain.board.model.Event;
import shop.donutmarket.donut.domain.participant.model.Participant;
import shop.donutmarket.donut.domain.user.model.User;

public class ParticipantReq {
    
    @Getter
    @Setter
    public static class ParticipantSaveReqDTO {

        @NotNull
        private Long eventId;
        private Long userId;
        @NotNull
        private int qty;
        @NotNull
        private LocalDateTime limitTime;
        private Long statusCodeId;

        public Participant toEntity(User user, Event event){
            return Participant.builder().event(event).user(user).qty(qty).limitTime(limitTime)
            .createdAt(LocalDateTime.now()).build();
        }
    }

    @Getter
    @Setter
    public static class ParticipantSelectReqDTO {
        @NotNull
        private Long id;
        @NotNull
        private Event event;
        @NotNull 
        private User user;
    }
    
    @Getter
    @Setter
    public static class ParticipantCancelReqDTO {
        @NotNull
        private Long id;
        @NotNull
        private User user;
    }

    @Getter
    @Setter
    public static class ParticipantDropReqDTO {
        @NotNull
        private Long id;
        @NotNull
        private Event event;
        @NotNull 
        private User user;
    }
}
