package shop.donutmarket.donut.domain.chat.dto;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import shop.donutmarket.donut.domain.chat.model.ChatterList;

public class ChatRespDto {
    
    @Getter
    @NoArgsConstructor
    public static class MyChatListRespDTO {
        private List<ChatterList> list;

        public MyChatListRespDTO(List<ChatterList> list) {
            this.list = list;
        }
    }
}
