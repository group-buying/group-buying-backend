package shop.donutmarket.donut.domain.user.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import shop.donutmarket.donut.domain.review.model.Rate;

@Getter
@Entity
@Table(name = "user_tb")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // 일반 유저는 username에 email 사용, oauth 유저는 oauth 아이디
    @Column(unique = true)
    private String username;
    @JsonIgnore
    private String password;
    private String email;
    private String name;
    private String profile;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rate_id")
    private Rate rate;
    private String role;
    // OAuth를 위해 필요한 필드
    private String provider;
    private String providerId;
    private Integer statusCode;
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Builder
    public User(Long id, String username, String password, String email, String name, String profile, Rate rate, String role, String provider, String providerId, Integer statusCode, LocalDateTime createdAt) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.name = name;
        this.profile = profile;
        this.rate = rate;
        this.role = role;
        this.provider = provider;
        this.providerId = providerId;
        this.statusCode = statusCode;
        this.createdAt = createdAt;
    }

    public void updateUser(String password, String name, String profile,
                           LocalDateTime createdAt) {
        this.password = password;
        this.name = name;
        this.profile = profile;
        this.createdAt = createdAt;
    }

    public void updateEmail(String email) {
        this.email = email;
    }

    public void updateStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }
}
