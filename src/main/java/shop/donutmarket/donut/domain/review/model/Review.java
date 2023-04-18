package shop.donutmarket.donut.domain.review.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class Review {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "reviewer_id")
    private Long reviewerId;
    @Column(name = "reviewed_id")
    private Long reviewedId;
    private int score;
    private String comment;
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Builder
    public Review(Long id, Long reviewerId, Long reviewedId, int score, String comment, LocalDateTime createdAt) {
        this.id = id;
        this.reviewerId = reviewerId;
        this.reviewedId = reviewedId;
        this.score = score;
        this.comment = comment;
        this.createdAt = createdAt;
    }
}
