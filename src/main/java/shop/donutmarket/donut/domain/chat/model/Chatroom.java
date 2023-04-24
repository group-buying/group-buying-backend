package shop.donutmarket.donut.domain.chat.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import shop.donutmarket.donut.domain.admin.model.StatusCode;

@Getter
@Entity
@NoArgsConstructor
public class Chatroom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "status_code")
    private StatusCode statusCode;
    private LocalDateTime createdAt;

    @Builder
    public Chatroom(Long id, StatusCode statusCode, LocalDateTime createdAt) {
        this.id = id;
        this.statusCode = statusCode;
        this.createdAt = createdAt;
    }
}
