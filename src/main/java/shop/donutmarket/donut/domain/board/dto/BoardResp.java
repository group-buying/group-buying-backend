package shop.donutmarket.donut.domain.board.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import shop.donutmarket.donut.domain.board.model.Board;
import shop.donutmarket.donut.domain.board.model.Event;
import shop.donutmarket.donut.domain.board.model.Tag;


public class BoardResp {
    
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class BoardDetailRespDTO {
        private Board board;
        private List<String> tagList;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class BoardSaveRespDTO {
        private Board board;
        private List<Tag> tagList;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class BoardUpdateRespDTO {
       // board
       private Long id;
       //event
       private int qty;
       private String paymentType;
       private LocalDateTime startAt;
       private LocalDateTime endAt;
       private int price;
       // tag
       private List<String> comment;
        
       public void updateRespDTO (int qty, String paymentType, LocalDateTime startAt, LocalDateTime endAt, int price, List<String> comment){
            this.qty = qty;
            this.paymentType = paymentType;
            this.startAt = startAt;
            this.endAt = endAt;
            this.price = price;
            this.comment = comment;
       }
    }
}

