package shop.donutmarket.donut.domain.payment.model;

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
public class Payment {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "participant_id")
    private Long participantId;
    @Column(name = "payment_type")
    private String paymentType;
    @Column(name = "status_code")
    private int statusCode;
    private boolean confirmed;
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Builder
    public Payment(Long id, Long participantId, String paymentType, int statusCode, boolean confirmed,
            LocalDateTime createdAt) {
        this.id = id;
        this.participantId = participantId;
        this.paymentType = paymentType;
        this.statusCode = statusCode;
        this.confirmed = confirmed;
        this.createdAt = createdAt;
    }
}
