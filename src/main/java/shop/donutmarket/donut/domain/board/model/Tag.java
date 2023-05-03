package shop.donutmarket.donut.domain.board.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

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
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long boardId;
    private String comment;
    @CreationTimestamp
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
