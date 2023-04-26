package shop.donutmarket.donut.domain.participant.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import shop.donutmarket.donut.domain.admin.model.StatusCode;
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
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "limit_time")
    private LocalDateTime limitTime;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "status_code")
    private StatusCode statusCode;

    @Builder
    public Participant(Long id, Event event, User user, int qty, LocalDateTime createdAt, LocalDateTime limitTime, StatusCode statusCode) {
        this.id = id;
        this.event = event;
        this.user = user;
        this.qty = qty;
        this.createdAt = createdAt;
        this.limitTime = limitTime;
        this.statusCode = statusCode;
    }

    public void updateParticipant(Event event, User user, int qty, LocalDateTime createdAt, LocalDateTime limitTime, StatusCode statusCode) {
        this.event = event;
        this.user = user;
        this.qty = qty;
        this.createdAt = createdAt;
        this.limitTime = limitTime;
        this.statusCode = statusCode;
    }

    public void selected() {
        StatusCode seletedCode = StatusCode.builder().id(302).type("participant")
        .status("채택").createdAt(LocalDateTime.now()).build();
        this.statusCode = seletedCode;
    }

    public void canceled() {
        StatusCode canceled = new StatusCode(303, "participant", "참가 취소", LocalDateTime.now());
        this.statusCode = canceled;
    }
}
