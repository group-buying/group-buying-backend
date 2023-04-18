package shop.donutmarket.donut.domain.review.model;

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
public class Rate {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "rate_name")
    private String rateName;
    @Column(name = "rate_point")
    private int ratPoint;
    @Column(name = "rate_img")
    private String rateImg;
    
    
    @Builder
    public Rate(Long id, Long userId, String rateName, int ratPoint, String rateImg) {
        this.id = id;
        this.userId = userId;
        this.rateName = rateName;
        this.ratPoint = ratPoint;
        this.rateImg = rateImg;
    }
}
