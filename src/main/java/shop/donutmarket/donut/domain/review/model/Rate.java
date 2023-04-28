package shop.donutmarket.donut.domain.review.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Rate {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // User쪽에서의 단방향 매핑만 허용
    private Long userId;
    private String rateName;
    private int ratePoint;
    @CreatedDate
    private LocalDateTime createdAt;

    @Builder
    public Rate(Long id, Long userId, String rateName, int ratePoint, LocalDateTime createdAt) {
        this.id = id;
        this.userId = userId;
        this.rateName = rateName;
        this.ratePoint = ratePoint;
        this.createdAt = createdAt;
    }

    public void updateRate(Long userId, String rateName, int ratePoint, LocalDateTime createdAt) {
        this.userId = userId;
        this.rateName = rateName;
        this.ratePoint = ratePoint;
        this.createdAt = createdAt;
    }

    public void rateUp(){
        this.ratePoint++;
    }
}
