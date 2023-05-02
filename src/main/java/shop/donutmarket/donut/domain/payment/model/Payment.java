package shop.donutmarket.donut.domain.payment.model;

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
public class Payment {
    // 연관관계 mapping 하지 않음
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;
    private Long paymentInfoId;
    @Column(name = "payment_type")
    private String paymentType;
    private Integer statusCode;
    private boolean confirmed;
    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Builder
    public Payment(Long id, User user, Event event, Long paymentInfoId, String paymentType, Integer statusCode, boolean confirmed, LocalDateTime createdAt) {
        this.id = id;
        this.user = user;
        this.event = event;
        this.paymentInfoId = paymentInfoId;
        this.paymentType = paymentType;
        this.statusCode = statusCode;
        this.confirmed = confirmed;
        this.createdAt = createdAt;
    }

    public void updateStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }
}
