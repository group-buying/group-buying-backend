package shop.donutmarket.donut.domain.participant.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import shop.donutmarket.donut.domain.admin.model.StatusCode;
import shop.donutmarket.donut.domain.board.model.Event;
import shop.donutmarket.donut.domain.participant.model.Participant;
import shop.donutmarket.donut.domain.user.model.User;

public class ParticipantReq {
    
    @Getter
    @Setter
    public static class ParticipantSaveReqDTO {

        @NotNull
        private Event event;
        private User user;
        @NotNull
        private int qty;
        @NotNull
        private LocalDateTime limitTime;
        private StatusCode statusCode;

        public Participant toEntity(User user){
            StatusCode statusCode = StatusCode.builder().id(300).type("participant")
            .status("참가").createdAt(LocalDateTime.now()).build();

            return Participant.builder().event(event).user(user).qty(qty).limitTime(limitTime)
            .statusCode(statusCode).createdAt(LocalDateTime.now()).build();
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
