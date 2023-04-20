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
        @NotBlank(message = "카테고리를 설정해주세요")
        private Category category;
        @NotBlank(message = "제목을 최소 2자이상 작성해주세요")
        @Size(min = 2)
        private String title;
        @NotBlank
        private User organizer;
        @NotBlank(message = "내용을 최소 2자이상 작성해주세요")
        @Size(min = 2)
        private String content;
        private String img;
        @NotBlank
        private StatusCode statusCode;
        @NotBlank(message = "위치를 설정해주세요")
        private String state;
        @NotBlank(message = "위치를 설정해주세요")
        private String city;
        @NotBlank(message = "위치를 설정해주세요")
        private String town;
        //event
        @NotBlank(message = "위치를 설정해주세요")
        private double latitude;
        @NotBlank(message = "위치를 설정해주세요")
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
        //event
        @NotBlank
        @Size(min = 2)(message = "수량을 설정해주세요")
        private int qty;
        @NotBlank(message = "결제방식을 설정해주세요")
        private String paymentType;
        private LocalDateTime startAt;
        @NotBlank(message = "시간을 설정해주세요")
        private LocalDateTime endAt;
        // tag
        private List<String> comment;

        public Board toBoardEntity(Event event){
            return Board.builder().id(id).event(event).createdAt(LocalDateTime.now()).build();
        }
        public Event toEventEntity(){
            return Event.builder().id(id).qty(qty).paymentType(paymentType)
            .startAt(startAt).endAt(endAt).build();
        }
    }
}
