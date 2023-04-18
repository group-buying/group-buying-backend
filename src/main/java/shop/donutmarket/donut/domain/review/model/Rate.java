package shop.donutmarket.donut.domain.review.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import shop.donutmarket.donut.domain.user.model.User;

import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Rate {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_id") // User쪽에서의 단방향 매핑만 허용 
    private Long userId;
    @Column(name = "rate_name")
    private String rateName;
    @Column(name = "rate_point")
    private int ratePoint;
    @Column(name = "rate_img")
    private String rateImg;
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Builder
    public Rate(Long id, Long userId, String rateName, int ratePoint, String rateImg, LocalDateTime createdAt) {
        this.id = id;
        this.userId = userId;
        this.rateName = rateName;
        this.ratePoint = ratePoint;
        this.rateImg = rateImg;
        this.createdAt = createdAt;
    }
}
