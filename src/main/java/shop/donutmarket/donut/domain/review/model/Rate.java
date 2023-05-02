package shop.donutmarket.donut.domain.review.model;

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
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Rate {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String rateName;
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Builder
    public Rate(Long id, String rateName, LocalDateTime createdAt) {
        this.id = id;
        this.rateName = rateName;
        this.createdAt = createdAt;
    }
}
