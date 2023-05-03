package shop.donutmarket.donut.domain.review.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import shop.donutmarket.donut.domain.user.model.User;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Review {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reviewer_id")
    private User reviewer;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reviewed_id")
    private User reviewed;
    private int score;
    private String comment;
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Builder
    public Review(Long id, User reviewer, User reviewed, int score, String comment, LocalDateTime createdAt) {
        this.id = id;
        this.reviewer = reviewer;
        this.reviewed = reviewed;
        this.score = score;
        this.comment = comment;
        this.createdAt = createdAt;
    }

    public void updateReview(User reviewer, User reviewed, int score, String comment, LocalDateTime createdAt) {
        this.reviewer = reviewer;
        this.reviewed = reviewed;
        this.score = score;
        this.comment = comment;
        this.createdAt = createdAt;
    }
}
