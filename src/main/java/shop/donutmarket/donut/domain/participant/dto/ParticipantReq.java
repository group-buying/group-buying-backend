package shop.donutmarket.donut.domain.participant.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
        @NotBlank
        private Long id;
        @NotBlank
        private Event event;
        @NotBlank
        private User user;

        public Participant toEntity(){
            StatusCode selected = new StatusCode(302, "participant", "채택", LocalDateTime.now());
            return Participant.builder().id(id).event(event).user(user).statusCode(selected).build();
        }
    }
}
