package shop.donutmarket.donut.domain.myCategory.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import shop.donutmarket.donut.domain.admin.model.Category;
import shop.donutmarket.donut.domain.user.model.User;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "my_category")
public class MyCategory {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Builder
    public MyCategory(Long id, User user, Category category, LocalDateTime createdAt) {
        this.id = id;
        this.user = user;
        this.category = category;
        this.createdAt = createdAt;
    }

    public void updateMyCategory(Category category, LocalDateTime createdAt) {
        this.category = category;
        this.createdAt = createdAt;
    }

    public static List<Long> defaultList(){
        List<Long> defaultList = new ArrayList<>();
        defaultList.add(1L); defaultList.add(2L); defaultList.add(3L); defaultList.add(5L);
        defaultList.add(6L); defaultList.add(7L); defaultList.add(12L); defaultList.add(14L);
        // 생활가전, 스포츠, 잡화, 의류, 식품, 미용, 편의점, 도매상
        return defaultList;
    }
}
