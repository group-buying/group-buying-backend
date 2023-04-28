package shop.donutmarket.donut.domain.payment.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PaymentInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long eventId;
    private Long userId;
    private String event;
    // 매번 new하게 해주어야 한다
    @Embedded
    private PaymentData data = new PaymentData();
    @CreatedDate
    private LocalDateTime createdAt;

    @Builder
    public PaymentInfo(Long id, Long eventId, Long userId, String event, PaymentData data, LocalDateTime createdAt) {
        this.id = id;
        this.eventId = eventId;
        this.userId = userId;
        this.event = event;
        this.data = data;
        this.createdAt = createdAt;
    }
}