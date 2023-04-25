package shop.donutmarket.donut.domain.chat.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import shop.donutmarket.donut.domain.chat.model.ChatterList;

public class ChatResp {
    
    @Getter
    @NoArgsConstructor
    public static class MyChatListRespDTO {
        private List<ChatterList> list;

        public MyChatListRespDTO(List<ChatterList> list) {
            this.list = list;
        }
    }

    @Getter
    @AllArgsConstructor
    public static class ChatterListFirebaseRespDTO {
        private Long chatroomId;
        private Long userId;
        private Long statusCode;
        private LocalDateTime createdAt;
    }
}
