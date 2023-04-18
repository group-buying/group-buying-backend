package shop.donutmarket.donut.domain.review.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import shop.donutmarket.donut.domain.review.model.Review;

public interface ReviewRepository extends JpaRepository<Review, Long>{
    
}
