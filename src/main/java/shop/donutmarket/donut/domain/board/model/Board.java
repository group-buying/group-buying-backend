package shop.donutmarket.donut.domain.board.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class Board {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "category_id")
    private Long categoryId;
    private String title;
    @Column(name = "organizer_id")
    private Long organizerId;
    private String content;
    private String img;
    @Column(name = "event_id")
    private Long eventId;
    @Column(name = "status_code")
    private int statusCode;
    private int views;
    private boolean recommend;
    private String state;
    private String city;
    private String town;
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Builder
    public Board(Long id, Long categoryId, String title, Long organizerId, String content, String img, Long eventId,
            int statusCode, int views, boolean recommend, String state, String city, String town,
            LocalDateTime createdAt) {
        this.id = id;
        this.categoryId = categoryId;
        this.title = title;
        this.organizerId = organizerId;
        this.content = content;
        this.img = img;
        this.eventId = eventId;
        this.statusCode = statusCode;
        this.views = views;
        this.recommend = recommend;
        this.state = state;
        this.city = city;
        this.town = town;
        this.createdAt = createdAt;
    }
}
