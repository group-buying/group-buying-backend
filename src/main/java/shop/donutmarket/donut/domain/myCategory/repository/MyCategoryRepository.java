package shop.donutmarket.donut.domain.myCategory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import shop.donutmarket.donut.domain.myCategory.model.MyCategory;

@Repository
public interface MyCategoryRepository extends JpaRepository<MyCategory, Long>{
    
}
