package shop.donutmarket.donut.domain.chat.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import shop.donutmarket.donut.domain.user.model.User;

@Getter
@Entity
@NoArgsConstructor
public class ChatterList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chatroom_id")
    private Chatroom chatroomId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User userId;
    private Integer statusCode;
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Builder
    public ChatterList(Long id, Chatroom chatroomId, User userId, Integer statusCode, LocalDateTime createdAt) {
        this.id = id;
        this.chatroomId = chatroomId;
        this.userId = userId;
        this.statusCode = statusCode;
        this.createdAt = createdAt;
    }
}
