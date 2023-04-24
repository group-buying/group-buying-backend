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
        @NotNull
        private Long invitedUserId;
        @NotNull
        private Long participantId;
        @NotNull
        private Long chatroomId;
    }
}
