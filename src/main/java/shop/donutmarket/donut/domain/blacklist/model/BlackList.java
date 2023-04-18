package shop.donutmarket.donut.domain.blacklist.model;

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
public class BlackList {
        
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "blocked_user_id")
    private Long blockedUserId;
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Builder
    public BlackList(Long id, Long userId, Long blockedUserId, LocalDateTime createdAt) {
        this.id = id;
        this.userId = userId;
        this.blockedUserId = blockedUserId;
        this.createdAt = createdAt;
    }

}
