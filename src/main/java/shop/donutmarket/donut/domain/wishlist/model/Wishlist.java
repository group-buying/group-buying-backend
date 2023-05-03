package shop.donutmarket.donut.domain.wishlist.model;

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
import shop.donutmarket.donut.domain.board.model.Board;
import shop.donutmarket.donut.domain.user.model.User;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Wishlist {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Builder
    public Wishlist(Long id, User user, Board board, LocalDateTime createdAt) {
        this.id = id;
        this.user = user;
        this.board = board;
        this.createdAt = createdAt;
    }

    public void updateWishlist(Board board, LocalDateTime createdAt) {
        this.board = board;
        this.createdAt = createdAt;
    }
}
