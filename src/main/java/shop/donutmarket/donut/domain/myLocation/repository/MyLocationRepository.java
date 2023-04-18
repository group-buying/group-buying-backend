package shop.donutmarket.donut.domain.myLocation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import shop.donutmarket.donut.domain.myLocation.model.MyLocation;

@Repository
public interface MyLocationRepository extends JpaRepository<MyLocation, Long>{
    
}
