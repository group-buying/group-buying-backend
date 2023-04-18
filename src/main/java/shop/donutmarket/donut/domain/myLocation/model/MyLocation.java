package shop.donutmarket.donut.domain.myLocation.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "my_location")
public class MyLocation {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_id")
    private Long userId;
    private String state;
    private String city;
    private String town;

    @Builder
    public MyLocation(Long id, Long userId, String state, String city, String town) {
        this.id = id;
        this.userId = userId;
        this.state = state;
        this.city = city;
        this.town = town;
    }
    
}
