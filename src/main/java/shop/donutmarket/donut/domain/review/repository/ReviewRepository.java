package shop.donutmarket.donut.domain.review.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import shop.donutmarket.donut.domain.review.model.Review;

public interface ReviewRepository extends JpaRepository<Review, Long>{

    @Query("select r from Review r left join fetch r.reviewed ed left join fetch ed.rate left join fetch r.reviewer er " +
            "left join fetch er.rate where r.reviewer.id = :userId")
    List<Review> findAllByReviewerId(@Param("userId") Long userId);

    @Query("select r from Review r join fetch r.reviewed join fetch r.reviewer where r.id = :id")
    Optional<Review> findByIdJoinFetch(@Param("id") Long id);

}
