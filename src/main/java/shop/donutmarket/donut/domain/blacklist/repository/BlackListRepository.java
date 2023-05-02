package shop.donutmarket.donut.domain.blacklist.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import shop.donutmarket.donut.domain.blacklist.model.Blacklist;
import shop.donutmarket.donut.domain.myPage.dto.MyPageResp;

import java.util.List;
import java.util.Optional;

public interface BlackListRepository extends JpaRepository<Blacklist, Long>{

    @Query("select b from Blacklist b where b.blockedUser.id = :blockedUserId and b.user.id = :userId")
    Optional<Blacklist> findByBlockedUserIdWithUserId(@Param("blockedUserId") Long blockedUserId, @Param("userId") Long userId);

    @Query("select b from Blacklist b left join fetch b.user u left join fetch u.rate " +
            "left join fetch b.blockedUser bu left join fetch bu.rate where b.user.id = :userId")
    List<Blacklist> findByUserId(@Param("userId") Long userId);
}
