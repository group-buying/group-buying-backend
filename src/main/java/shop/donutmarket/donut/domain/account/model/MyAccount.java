package shop.donutmarket.donut.domain.account.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "my_account")
public class MyAccount {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_id")
    private Long userId;
    private String brand;
    @Column(name = "account_number")
    private String accountNumber;
    private LocalDateTime createdAt;

    @Builder
    public MyAccount(Long id, Long userId, String brand, String accountNumber, LocalDateTime createdAt) {
        this.id = id;
        this.userId = userId;
        this.brand = brand;
        this.accountNumber = accountNumber;
        this.createdAt = createdAt;
    }
}
