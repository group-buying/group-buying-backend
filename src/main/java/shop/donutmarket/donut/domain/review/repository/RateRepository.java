package shop.donutmarket.donut.domain.review.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import shop.donutmarket.donut.domain.review.model.Rate;


public interface RateRepository extends JpaRepository<Rate, Long>{

}
