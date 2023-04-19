package shop.donutmarket.donut.domain.board.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
import shop.donutmarket.donut.domain.admin.model.Category;
import shop.donutmarket.donut.domain.admin.model.StatusCode;
import shop.donutmarket.donut.domain.user.model.User;

@Getter
@Setter
public class BoardReq {
    
    @Getter
    @Setter
    public class BoardSaveReqDTO {
        // board
        private Category category;
        private String title;
        private User organizer;
        private String content;
        private String img;
        private StatusCode statusCode;
        private String state;
        private String city;
        private String town;
        //event
        private double latitude;
        private double longtitude;
        private int qty;
        private String paymentType;
        private LocalDateTime endAt;
        private int price;
        // tag
        private String comment;
    }
}
