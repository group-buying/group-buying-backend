package shop.donutmarket.donut.domain.main.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;
import shop.donutmarket.donut.domain.admin.model.Category;
import shop.donutmarket.donut.domain.admin.model.StatusCode;
import shop.donutmarket.donut.domain.board.model.Board;
import shop.donutmarket.donut.domain.myCategory.model.MyCategory;
import shop.donutmarket.donut.domain.myLocation.model.MyLocation;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class MainResp {
    private List<MyCategoryDTO> myCategories;
    private MyLocationDTO myLocation;
    private List<BoardDTO> boards;

    public MainResp(List<MyCategory> myCategories,
                    MyLocationDTO myLocation,
                    List<Board> boards) {
        this.myCategories = myCategories
                .stream().map(myCategory -> new MyCategoryDTO(myCategory))
                .collect(Collectors.toList());
        this.myLocation = myLocation;
        this.boards = boards
                .stream().map((board -> new BoardDTO(board)))
                .collect(Collectors.toList());
    }

    public MainResp(List<MyCategory> myCategories,
                    List<Board> boards) {
        this.myCategories = myCategories
                .stream().map(myCategory -> new MyCategoryDTO(myCategory))
                .collect(Collectors.toList());
        this.boards = boards
                .stream().map((board -> new BoardDTO(board)))
                .collect(Collectors.toList());
    }

    @Getter
    @Setter
    public static class BoardDTO {
        private String title;
        private StatusCode statusCode;
        private String state;
        private String city;
        private String town;
        private int price;
        private int qty;
        private String paymentType;
        private boolean recommend;
        private LocalDateTime endAt;

        public BoardDTO(Board board) {
            this.title = board.getTitle();
            this.statusCode = board.getStatusCode();
            this.state = board.getState();
            this.city = board.getCity();
            this.town = board.getTown();
            this.price = board.getEvent().getPrice();
            this.qty = board.getEvent().getQty();
            this.paymentType = board.getEvent().getPaymentType();
            this.recommend = board.isRecommend();
            this.endAt = board.getEvent().getEndAt();
        }
    }

    @Getter
    @Setter
    public static class MyCategoryDTO {
        private String name;

        public MyCategoryDTO(MyCategory myCategory) {
            this.name = myCategory.getCategory().getName();
        }
    }

    @Getter
    @Setter
    public static class MyLocationDTO {
        private String town;

        public MyLocationDTO(MyLocation myLocation) {
            this.town = myLocation.getTown();
        }
    }
}
