package shop.donutmarket.donut.domain.payment.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import shop.donutmarket.donut.domain.admin.model.StatusCode;
import shop.donutmarket.donut.domain.participant.model.Participant;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private Long eventId;
    private Long paymentInfoId;
    @Column(name = "payment_type")
    private String paymentType;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "status_code")
    private StatusCode statusCode;
    private boolean confirmed;
    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Builder
    public Payment(Long id, Long userId, Long eventId, Long paymentInfoId, String paymentType, StatusCode statusCode, boolean confirmed, LocalDateTime createdAt) {
        this.id = id;
        this.userId = userId;
        this.eventId = eventId;
        this.paymentInfoId = paymentInfoId;
        this.paymentType = paymentType;
        this.statusCode = statusCode;
        this.confirmed = confirmed;
        this.createdAt = createdAt;
    }
}
