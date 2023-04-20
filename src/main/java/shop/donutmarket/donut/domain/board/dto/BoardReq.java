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
        @NotBlank
        private Category category;
        @NotBlank
        @Size(min = 2)
        private String title;
        @NotBlank
        private User organizer;
        @NotBlank
        @Size(min = 2)
        private String content;
        private String img;
        @NotBlank
        private StatusCode statusCode;
        @NotBlank
        private String state;
        @NotBlank
        private String city;
        @NotBlank
        private String town;
        //event
        @NotBlank
        private double latitude;
        @NotBlank
        private double longtitude;
        @NotBlank
        @Size(min = 2)
        private int qty;
        @NotBlank
        private String paymentType;
        @NotBlank
        private LocalDateTime endAt;
        @NotBlank
        @Size(min = 10)
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
    @AllArgsConstructor
    public static class BoardUpdateReqDTO {
        // board
        @NotBlank
        private Long id;
        @NotBlank
        private Category category;
        @NotBlank
        @Size(min = 2)
        private String title;
        @NotBlank
        @Size(min = 2)
        private String content;
        private String img;
        @NotBlank
        private StatusCode statusCode;
        @NotBlank
        private String state;
        @NotBlank
        private String city;
        @NotBlank
        private String town;
        //event
        @NotBlank
        private double latitude;
        @NotBlank
        private double longtitude;
        @NotBlank
        @Size(min = 2)
        private int qty;
        @NotBlank
        private String paymentType;
        private LocalDateTime startAt;
        @NotBlank
        private LocalDateTime endAt;
        @NotBlank
        @Size(min = 10)
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
