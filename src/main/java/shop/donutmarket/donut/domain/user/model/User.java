package shop.donutmarket.donut.domain.user.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import shop.donutmarket.donut.domain.admin.model.StatusCode;
import shop.donutmarket.donut.domain.review.model.Rate;

@Getter
@Entity
@Table(name = "user_tb")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonIgnore
    private String password;
    @Column(unique = true)
    private String email;
    private String name;
    private String profile;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rate_id")
    private Rate rate;
    private boolean type;
    private String role;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "status_code")
    private StatusCode statusCode;
    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;


    @Builder
    public User(Long id, String password, String email, String name, String profile, Rate rate, boolean type, String role, StatusCode statusCode, LocalDateTime createdAt) {
        this.id = id;
        this.password = password;
        this.email = email;
        this.name = name;
        this.profile = profile;
        this.rate = rate;
        this.type = type;
        this.role = role;
        this.statusCode = statusCode;
        this.createdAt = createdAt;
    }

    public void updateUser(String password, String name, String profile, LocalDateTime createdAt) {
        this.password = password;
        this.name = name;
        this.profile = profile;
        this.createdAt = createdAt;
    }
}
