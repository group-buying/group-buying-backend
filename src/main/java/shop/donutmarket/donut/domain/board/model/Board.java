package shop.donutmarket.donut.domain.board.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;
import shop.donutmarket.donut.domain.admin.model.Category;
import shop.donutmarket.donut.domain.admin.model.StatusCode;
import shop.donutmarket.donut.domain.user.model.User;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Board {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;
    private String title;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organizer_id")
    private User organizer;
    private String content;
    private String img;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id")
    private Event event;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "status_code")
    private StatusCode statusCode;
    private int views;
    private boolean recommend;
    private String state;
    private String city;
    private String town;
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Builder
    public Board(Long id, Category category, String title, User organizer, String content, String img, Event event, StatusCode statusCode, int views, boolean recommend, String state, String city, String town, LocalDateTime createdAt) {
        this.id = id;
        this.category = category;
        this.title = title;
        this.organizer = organizer;
        this.content = content;
        this.img = img;
        this.event = event;
        this.statusCode = statusCode;
        this.views = views;
        this.recommend = recommend;
        this.state = state;
        this.city = city;
        this.town = town;
        this.createdAt = createdAt;
    }

    public void deleteBoard() {
        StatusCode deletedCode = StatusCode.builder().id(203).type("board")
        .status("삭제").createdAt(LocalDateTime.now()).build();
        this.statusCode = deletedCode;
    }

}
