package shop.donutmarket.donut.domain.participant.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import shop.donutmarket.donut.domain.board.model.Event;
import shop.donutmarket.donut.domain.user.model.User;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Participant {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id")
    private Event event;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    private int qty;
    private LocalDateTime limitTime;
    private Integer statusCode;
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Builder
    public Participant(Long id, Event event, User user, int qty, LocalDateTime createdAt, LocalDateTime limitTime, Integer statusCode) {
        this.id = id;
        this.event = event;
        this.user = user;
        this.qty = qty;
        this.createdAt = createdAt;
        this.limitTime = limitTime;
        this.statusCode = statusCode;
    }

    public void updateParticipant(Event event, User user, int qty, LocalDateTime createdAt, LocalDateTime limitTime, Integer statusCode) {
        this.event = event;
        this.user = user;
        this.qty = qty;
        this.createdAt = createdAt;
        this.limitTime = limitTime;
        this.statusCode = statusCode;
    }

    public void updateStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }
}
