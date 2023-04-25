package shop.donutmarket.donut.domain.myLocation.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import shop.donutmarket.donut.domain.myLocation.model.MyLocation;

public interface MyLocationRepository extends JpaRepository<MyLocation, Long>{
    
    @Query("select m from MyLocation m where m.user.id = :userId")
    Optional<MyLocation> findByUserId(@Param("userId") Long userId);
}
