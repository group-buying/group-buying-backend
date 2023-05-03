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
        @NotNull(message = "카테고리를 선택해주세요.")
        private Category category;
        @NotBlank(message = "제목을 입력해주세요.")
        private String title;
        private User organizer;
        @NotBlank(message = "내용을 입력해주세요.")
        private String content;
        private String img;
        @NotBlank(message = "위치를 입력해주세요.")
        private String state;
        @NotBlank(message = "위치를 입력해주세요.")
        private String city;
        @NotBlank(message = "위치를 입력해주세요.")
        private String town;
        //event
        @NotNull(message = "위치를 입력해주세요.")
        private double latitude;
        @NotNull(message = "위치를 입력해주세요.")
        private double longtitude;
        @NotNull(message = "수량을 입력해주세요.")
        private int qty;
        @NotBlank(message = "결제 방식을 선택해주세요.")
        private String paymentType;
        @NotNull(message = "종료일을 선택해주세요.")
        private LocalDateTime endAt;
        @NotNull(message = "가격을 입력해주세요.")
        private int price;
        // tag
        private List<String> comment;

        public Board toBoardEntity(Event event, String Base64img, User organizer){
            return Board.builder().category(category).title(title).organizer(organizer).content(content).img(Base64img)
            .event(event).views(0).recommend(false).state(state).city(city).town(town).createdAt(LocalDateTime.now()).build();
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
        @NotNull(message = "게시글ID를 입력해주세요.")
        private Long id;
        //event
        @NotNull(message = "수량을 입력해주세요.")
        private int qty;
        @NotBlank(message = "결제 방식을 선택해주세요.")
        private String paymentType;
        private LocalDateTime endAt;
        @NotNull(message = "가격을 입력해주세요.")
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
        @NotNull(message = "게시글ID를 입력해주세요.")
        private Long boardId;
    }
}

