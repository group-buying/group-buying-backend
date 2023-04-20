package shop.donutmarket.donut.domain.participant.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
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
        @NotBlank
        private Event event;
        @NotBlank
        private User user;
        @NotBlank
        @Size(min = 1)
        private int qty;
        @NotBlank
        private LocalDateTime limitTime;
        @NotBlank
        private StatusCode statusCode;

        public Participant toEntity(){
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
