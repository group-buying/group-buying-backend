package shop.donutmarket.donut.domain.myCategory.model;

import jakarta.persistence.*;
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
    @Column(name = "user_id")
    private User user;
    @OneToOne(fetch = FetchType.LAZY)
    @Column(name = "category_id")
    private Category category;

    @Builder
    public MyCategory(Long id, User user, Category category) {
        this.id = id;
        this.user = user;
        this.category = category;
    }
}
