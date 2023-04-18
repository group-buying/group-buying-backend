package shop.donutmarket.donut.domain.user.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    @JsonIgnore
    private String password;
    private String email;
    private String name;
    private String profile;
    @Column(name = "rate_id")
    private Long rateId;
    private String type;
    private String role;
    @Column(name = "status_code")
    private int statusCode;
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Builder
    public User(Long id, String username, String password, String email, String name, String profile, Long rateId,
            String type, String role, int statusCode, LocalDateTime createdAt) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.name = name;
        this.profile = profile;
        this.rateId = rateId;
        this.type = type;
        this.role = role;
        this.statusCode = statusCode;
        this.createdAt = createdAt;
    }
}
