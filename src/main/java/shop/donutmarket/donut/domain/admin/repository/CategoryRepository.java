package shop.donutmarket.donut.domain.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import shop.donutmarket.donut.domain.admin.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    
}
