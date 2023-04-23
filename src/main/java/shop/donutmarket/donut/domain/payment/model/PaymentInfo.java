package shop.donutmarket.donut.domain.payment.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PaymentInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String event;
    @Embedded
    private PaymentData data;
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Builder
    public PaymentInfo(Long id, String event, PaymentData data, LocalDateTime createdAt) {
        this.id = id;
        this.event = event;
        this.data = data;
        this.createdAt = createdAt;
    }
}