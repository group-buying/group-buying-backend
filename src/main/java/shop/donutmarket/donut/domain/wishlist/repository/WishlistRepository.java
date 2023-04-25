package shop.donutmarket.donut.domain.wishlist.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import shop.donutmarket.donut.domain.wishlist.model.Wishlist;

public interface WishlistRepository extends JpaRepository<Wishlist, Long>{
    
    @Query("select w from Wishlist w join fetch w.board b join fetch b.organizer join fetch b.event where w.user.id = :userId")
    List<Wishlist> findAllByUserId(@Param("userId") Long userId);
}
