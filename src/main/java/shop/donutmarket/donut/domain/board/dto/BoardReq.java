package shop.donutmarket.donut.domain.board.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import shop.donutmarket.donut.domain.admin.model.Category;
import shop.donutmarket.donut.domain.admin.model.StatusCode;
import shop.donutmarket.donut.domain.board.model.Board;
import shop.donutmarket.donut.domain.board.model.Event;
import shop.donutmarket.donut.domain.user.model.User;

public class BoardReq {
    
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class BoardSaveReqDTO {
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
        private List<String> comment;

        public Board toBoardEntity(Event event, String Base64img){
            return Board.builder().category(category).title(title).organizer(organizer).content(content).img(Base64img)
            .event(event).statusCode(statusCode).views(0).recommend(false).state(state).city(city).town(town).createdAt(LocalDateTime.now()).build();
        }

        public Event toEventEntity(){
            return Event.builder().latitude(latitude).longtitude(longtitude).qty(qty).paymentType(paymentType)
            .startAt(LocalDateTime.now()).endAt(endAt).price(price).statusCode(statusCode).build();
        }
    }

    @Getter
    @Setter
    public static class BoardUpdateReqDTO {
        // board
        private Long id;
        private Category category;
        private String title;
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
        private LocalDateTime startAt;
        private LocalDateTime endAt;
        private int price;
        // tag
        private List<String> comment;

        public Board toBoardEntity(Event event, String Base64img){
            return Board.builder().id(id).category(category).title(title).event(event).img(Base64img).content(content)
            .statusCode(statusCode).state(state).city(city).town(town).createdAt(LocalDateTime.now()).build();
        }
        public Event toEventEntity(){
            return Event.builder().id(id).latitude(latitude).longtitude(longtitude).qty(qty)
            .paymentType(paymentType).startAt(startAt).endAt(endAt).price(price).build();
        }
    }
}
