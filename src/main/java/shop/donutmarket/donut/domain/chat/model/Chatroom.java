package shop.donutmarket.donut.domain.chat.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;
import shop.donutmarket.donut.domain.admin.model.StatusCode;
import shop.donutmarket.donut.domain.board.model.Event;

@Getter
@Entity
@NoArgsConstructor
public class Chatroom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id")
    private Event event;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "status_code")
    private StatusCode statusCode;
    @CreatedDate
    private LocalDateTime createdAt;

    @Builder
    public Chatroom(Long id, Event event, StatusCode statusCode, LocalDateTime createdAt) {
        this.id = id;
        this.event = event;
        this.statusCode = statusCode;
        this.createdAt = createdAt;
    }

    public void active(){
        StatusCode activeCode = StatusCode.builder().id(500).type("chatroom").status("활성화").build();
        this.statusCode = activeCode;
    }
}
