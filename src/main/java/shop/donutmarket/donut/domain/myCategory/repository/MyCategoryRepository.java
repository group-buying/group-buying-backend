package shop.donutmarket.donut.domain.myCategory.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import shop.donutmarket.donut.domain.myCategory.model.MyCategory;

public interface MyCategoryRepository extends JpaRepository<MyCategory, Long>{
    
    @Modifying
    @Query("Delete from MyCategory m where m.user.id = :userId")
    void deleteAllByUserId(@Param("userId") Long userId);

    @Query("select m from MyCategory m where m.user.id = :userId")
    List<MyCategory> findByUserId(@Param("userId") Long userId);

}
