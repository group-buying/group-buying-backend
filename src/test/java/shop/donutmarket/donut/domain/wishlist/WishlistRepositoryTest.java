package shop.donutmarket.donut.domain.wishlist;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

import shop.donutmarket.donut.domain.user.UserConst;
import shop.donutmarket.donut.domain.wishlist.model.Wishlist;
import shop.donutmarket.donut.domain.wishlist.repository.WishlistRepository;

@DataJpaTest
@Transactional
public class WishlistRepositoryTest {
    
    @Autowired
    private WishlistRepository wishlistRepository;

    @BeforeEach
    void setUp(){
        // Wishlist wishlist = Wishlist.builder().id(1L).user(new UserConst()).board(new BoardConst()).build();
        // wishlistRepository.save(wishlist);
    }
    
    
    @Test
    void findById_Test(){
        // given
        Long id = 1L;
        
        // when
        Optional<Wishlist> wishlist = wishlistRepository.findById(id);
        
        // then
        assertNotNull(wishlist);
    }
    
    @Test
    void save_Test() {
        // given
        Long id = 2L;
        // Wishlist wishlist = Wishlist.builder().id(id).user(new UserConst()).board().build();
        
        // when
        // wishlistRepository.save(wishlist);
        
        // then
        assertNotNull(wishlistRepository.findById(id));
    }

    @Test
    void deleteById_Test(){
        // given
        Long id = 1L;
        
        // when
        wishlistRepository.deleteById(id);

        // then
        assertEquals(Optional.empty(), wishlistRepository.findById(id));
    }
}
