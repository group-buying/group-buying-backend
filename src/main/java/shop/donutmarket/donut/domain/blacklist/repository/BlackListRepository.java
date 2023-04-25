package shop.donutmarket.donut.domain.blacklist.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import shop.donutmarket.donut.domain.blacklist.model.Blacklist;

import java.util.Optional;

public interface BlackListRepository extends JpaRepository<Blacklist, Long>{

    @Query("select b From Blacklist b where b.id = :id and b.user.id = :userId")
    Optional<Blacklist> findByIdWithUserId(@Param("id") Long id, @Param("userId") Long userId);
    
}
