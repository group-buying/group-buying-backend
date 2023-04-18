package shop.donutmarket.donut.domain.board.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import shop.donutmarket.donut.domain.admin.model.StatusCode;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Event {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double latitude;
    private double longtitude;
    private int qty;
    @Column(name = "payment_type")
    private String paymentType;
    @Column(name = "start_at")
    private LocalDateTime startAt;
    @Column(name = "end_at")
    private LocalDateTime endAt;
    @OneToOne(fetch = FetchType.LAZY)
    @Column(name = "status_code")
    private StatusCode statusCode;
    private int price;
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Builder
    public Event(Long id, double latitude, double longtitude, int qty, String paymentType, LocalDateTime startAt, LocalDateTime endAt, StatusCode statusCode, int price, LocalDateTime createdAt) {
        this.id = id;
        this.latitude = latitude;
        this.longtitude = longtitude;
        this.qty = qty;
        this.paymentType = paymentType;
        this.startAt = startAt;
        this.endAt = endAt;
        this.statusCode = statusCode;
        this.price = price;
        this.createdAt = createdAt;
    }
}
