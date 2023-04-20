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
    @AllArgsConstructor
    public static class ParticipantSaveReqDTO {
        private Event event;
        private User user;
        private int qty;
        private LocalDateTime limitTime;
        private StatusCode statusCode;

        public Participant toEntity(){
            return Participant.builder().event(event).user(user).qty(qty).limitTime(limitTime)
            .statusCode(statusCode).createdAt(LocalDateTime.now()).build();
        }
    }
}
