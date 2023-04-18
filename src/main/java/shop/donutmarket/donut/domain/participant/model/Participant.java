package shop.donutmarket.donut.domain.participant.model;

import java.time.LocalDateTime;

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
public class Participant {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "event_id")
    private Long eventId;
    @Column(name = "user_id")
    private Long userId;
    private int qty;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "limit_time")
    private LocalDateTime limitTime;
    @Column(name = "status_code")
    private int statusCode;

    @Builder
    public Participant(Long id, Long eventId, Long userId, int qty, LocalDateTime createdAt, LocalDateTime limitTime,
            int statusCode) {
        this.id = id;
        this.eventId = eventId;
        this.userId = userId;
        this.qty = qty;
        this.createdAt = createdAt;
        this.limitTime = limitTime;
        this.statusCode = statusCode;
    }
}
