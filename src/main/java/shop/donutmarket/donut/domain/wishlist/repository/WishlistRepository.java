package shop.donutmarket.donut.domain.wishlist.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import shop.donutmarket.donut.domain.wishlist.model.Wishlist;

public interface WishlistRepository extends JpaRepository<Wishlist, Long>{
    
    @Query("select w from Wishlist w left join fetch w.user u left join fetch u.rate " +
            "left join fetch w.board b left join fetch b.organizer o left join fetch o.rate " +
            "left join fetch b.event left join fetch b.category where w.user.id = :userId")
    List<Wishlist> findAllByUserId(@Param("userId") Long userId);
}
