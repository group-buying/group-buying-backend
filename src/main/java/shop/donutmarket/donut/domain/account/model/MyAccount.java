package shop.donutmarket.donut.domain.account.model;

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
@Table(name = "my_account")
public class MyAccount {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    private String brand;
    @Column(name = "account_number")
    private String accountNumber;
    @Column(name = "created_at")
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
