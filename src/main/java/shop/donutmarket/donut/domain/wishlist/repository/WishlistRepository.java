package shop.donutmarket.donut.domain.wishlist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import shop.donutmarket.donut.domain.wishlist.model.Wishlist;

@Repository
public interface WishlistRepository extends JpaRepository<Wishlist, Long>{
    
}
