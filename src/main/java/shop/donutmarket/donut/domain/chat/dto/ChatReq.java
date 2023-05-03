package shop.donutmarket.donut.domain.chat.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class ChatReq {
    
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ChatInviteReqDTO {
        @NotNull(message = "초대하려는 유저 ID를 입력해주세요.")
        private Long invitedUserId;
        @NotNull(message = "참가ID를 입력해주세요.")
        private Long participantId;
        @NotNull(message = "참가하려는 채팅방 ID를 입력해주세요.")
        private Long chatroomId;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ChatKickReqDTO {
        @NotNull(message = "강퇴하려는 참여자ID를 입력해주세요.")
        private Long chatterListId;
        @NotNull(message = "해당 이벤트ID를 입력해주세요.")
        private Long eventId;
    }
}
