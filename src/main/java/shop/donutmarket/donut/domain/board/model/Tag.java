package shop.donutmarket.donut.domain.board.model;

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
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "board_id")
    private Long boardId;
    private String comment;

    @Builder
    public Tag(Long id, Long boardId, String comment) {
        this.id = id;
        this.boardId = boardId;
        this.comment = comment;
    }
}
