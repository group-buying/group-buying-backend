package shop.donutmarket.donut.domain.board.dto;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
        @NotNull
        private Category category;
        @NotBlank
        private String title;
        private User organizer;
        @NotBlank
        private String content;
        private String img;
        private StatusCode statusCode;
        @NotBlank
        private String state;
        @NotBlank
        private String city;
        @NotBlank
        private String town;
        //event
        @NotNull
        private double latitude;
        @NotNull
        private double longtitude;
        @NotNull
        private int qty;
        @NotBlank
        private String paymentType;
        @NotNull
        private LocalDateTime endAt;
        @NotNull
        private int price;
        // tag
        private List<String> comment;

        public Board toBoardEntity(Event event, String Base64img, User organizer){
            StatusCode code = StatusCode.builder().id(200).type("board").status("진행중").build();
            
            return Board.builder().category(category).title(title).organizer(organizer).content(content).img(Base64img)
            .event(event).statusCode(code).views(0).recommend(false).state(state).city(city).town(town).createdAt(LocalDateTime.now()).build();
        }

        public Event toEventEntity(){
            return Event.builder().latitude(latitude).longtitude(longtitude).qty(qty).paymentType(paymentType)
            .startAt(LocalDateTime.now()).endAt(endAt).price(price).createdAt(LocalDateTime.now()).build();
        }
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class BoardUpdateReqDTO {
        // board
        @NotNull
        private Long id;
        //event
        @NotNull
        private int qty;
        @NotBlank
        private String paymentType;
        @NotNull
        private LocalDateTime startAt;
        @NotNull
        private LocalDateTime endAt;
        private int price;
        // tag
        private List<String> comment;

    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class BoardDeleteReqDTO {
        // board
        @NotNull
        private Long boardId;
    }
}

