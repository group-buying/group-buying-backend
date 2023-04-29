package shop.donutmarket.donut.domain.blacklist.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;
import shop.donutmarket.donut.domain.user.model.User;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Blacklist {
        
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "blocked_user_id")
    private User blockedUser;
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Builder
    public Blacklist(Long id, User user, User blockedUser, LocalDateTime createdAt) {
        this.id = id;
        this.user = user;
        this.blockedUser = blockedUser;
        this.createdAt = createdAt;
    }
}
