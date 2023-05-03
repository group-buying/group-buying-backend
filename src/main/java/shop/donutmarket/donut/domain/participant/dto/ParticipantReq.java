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
        @NotNull(message = "이벤트ID를 입력해주세요.")
        private Long eventId;
        @NotNull(message = "참가유저ID를 입력해주세요.")
        private Long userId;
        @NotNull(message = "수량을 입력해주세요.")
        private int qty;
        @NotNull(message = "제한 시간을 입력해주세요.")
        private LocalDateTime limitTime;

        public Participant toEntity(User user, Event event){
            return Participant.builder().event(event).user(user).qty(qty).limitTime(limitTime)
            .createdAt(LocalDateTime.now()).build();
        }
    }

    @Getter
    @Setter
    public static class ParticipantSelectReqDTO {
        @NotNull(message = "참가ID를 입력해주세요.")
        private Long participantId;
        @NotNull(message = "이벤트ID를 입력해주세요.")
        private Long eventId;
    }
    
    @Getter
    @Setter
    public static class ParticipantCancelReqDTO {
        @NotNull(message = "참가ID를 입력해주세요.")
        private Long participantId;
        @NotNull(message = "이벤트ID를 입력해주세요.")
        private Long eventId;
    }

    @Getter
    @Setter
    public static class ParticipantDropReqDTO {
        @NotNull(message = "참가ID를 입력해주세요.")
        private Long participantId;
        @NotNull(message = "이벤트ID를 입력해주세요.")
        private Long eventId;
    }
}
