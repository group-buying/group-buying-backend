package shop.donutmarket.donut.domain.blacklist.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import shop.donutmarket.donut.domain.blacklist.model.Blacklist;
import shop.donutmarket.donut.domain.myPage.dto.MyPageResp;

import java.util.List;
import java.util.Optional;

public interface BlackListRepository extends JpaRepository<Blacklist, Long>{

    @Query("select b from Blacklist b where b.id = :id and b.user.id = :userId")
    Optional<Blacklist> findByIdWithUserId(@Param("id") Long id, @Param("userId") Long userId);

    @Query("select b from Blacklist b where b.user.id = :userId")
    List<MyPageResp.MyBlacklistDTO> findByUserId(@Param("userId") Long userId);
}
