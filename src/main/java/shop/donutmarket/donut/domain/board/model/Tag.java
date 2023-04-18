package shop.donutmarket.donut.domain.board.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @Column(name = "board_id")
    private Board board;
    private String comment;
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Builder
    public Tag(Long id, Board board, String comment, LocalDateTime createdAt) {
        this.id = id;
        this.board = board;
        this.comment = comment;
        this.createdAt = createdAt;
    }
}
