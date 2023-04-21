package shop.donutmarket.donut.domain.board.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
    private int price;
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Builder
    public Event(Long id, double latitude, double longtitude, int qty, String paymentType, LocalDateTime startAt, LocalDateTime endAt, int price, LocalDateTime createdAt) {
        this.id = id;
        this.latitude = latitude;
        this.longtitude = longtitude;
        this.qty = qty;
        this.paymentType = paymentType;
        this.startAt = startAt;
        this.endAt = endAt;
        this.price = price;
        this.createdAt = createdAt;
    }

    public void updateEvent(int qty, String paymentType, LocalDateTime startAt, LocalDateTime endAt) {
        this.qty = qty;
        this.paymentType = paymentType;
        this.startAt = startAt;
        this.endAt = endAt;
    }
}