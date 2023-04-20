package shop.donutmarket.donut.domain.review.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import shop.donutmarket.donut.domain.review.model.Rate;


public interface RateRepository extends JpaRepository<Rate, Long>{

    
}
