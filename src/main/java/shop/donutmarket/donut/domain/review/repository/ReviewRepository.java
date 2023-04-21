package shop.donutmarket.donut.domain.review.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import shop.donutmarket.donut.domain.review.model.Review;

public interface ReviewRepository extends JpaRepository<Review, Long>{

    @Query("select r from Review r where r.reviewer.id = :userId")
    List<Review> findAllByReviewerId(@Param("userId") Long userId);
    
}
