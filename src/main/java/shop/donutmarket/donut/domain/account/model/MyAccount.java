package shop.donutmarket.donut.domain.account.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.LastModifiedDate;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import shop.donutmarket.donut.domain.user.model.User;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "my_account")
public class MyAccount {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    private String brand;
    private String accountNumber;
    @LastModifiedDate
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Builder
    public MyAccount(Long id, User user, String brand, String accountNumber, LocalDateTime createdAt) {
        this.id = id;
        this.user = user;
        this.brand = brand;
        this.accountNumber = accountNumber;
        this.createdAt = createdAt;
    }

    public void updateMyAccount(String brand, String accountNumber) {
        this.brand = brand;
        this.accountNumber = accountNumber;
    }
}
