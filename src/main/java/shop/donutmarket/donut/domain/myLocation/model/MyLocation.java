package shop.donutmarket.donut.domain.myLocation.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import shop.donutmarket.donut.domain.user.model.User;

import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "my_location")
public class MyLocation {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(fetch = FetchType.LAZY)
    @Column(name = "user_id")
    private User user;
    private String state;
    private String city;
    private String town;
    @Column(name = "created_at")
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
}
