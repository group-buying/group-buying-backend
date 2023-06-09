package shop.donutmarket.donut.domain.myLocation.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import shop.donutmarket.donut.domain.user.model.User;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "my_location")
public class MyLocation {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    private String state;
    private String city;
    private String town;
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Builder
    public MyLocation(Long id, User user, String state, String city, String town, LocalDateTime createdAt) {
        this.id = id;
        this.user = user;
        this.state = state;
        this.city = city;
        this.town = town;
        this.createdAt = createdAt;
    }

    public void updateMyLocation(String state, String city, String town, LocalDateTime createdAt) {
        this.state = state;
        this.city = city;
        this.town = town;
        this.createdAt = createdAt;
    }
    
    public void defaultLocation(){
        this.state = "부산광역시";
        this.city = "부산진구";
        this.town = "부전동";
        this.createdAt = LocalDateTime.now();
    }
}
