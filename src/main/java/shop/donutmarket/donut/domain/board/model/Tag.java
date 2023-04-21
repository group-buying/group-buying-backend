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
    @Column(name = "board_id")
    private Long boardId;
    private String comment;
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Builder
    public Tag(Long id, Long boardId, String comment, LocalDateTime createdAt) {
        this.id = id;
        this.boardId = boardId;
        this.comment = comment;
        this.createdAt = createdAt;
    }

    public void updateTag(Long boardId, String comment, LocalDateTime createdAt) {
        this.boardId = boardId;
        this.comment = comment;
        this.createdAt = createdAt;
    }
}
