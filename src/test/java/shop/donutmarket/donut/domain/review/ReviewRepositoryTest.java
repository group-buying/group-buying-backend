package shop.donutmarket.donut.domain.review;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

import shop.donutmarket.donut.domain.review.model.Review;
import shop.donutmarket.donut.domain.review.repository.ReviewRepository;

@DataJpaTest
@Transactional
public class ReviewRepositoryTest {
    
    @Autowired
    private ReviewRepository reviewRepository;

    @BeforeEach
    void setUp(){
        Review review = Review.builder().id(1L).reviewerId(1L).reviewedId(2L).score(5).comment("리뷰내용").createdAt(LocalDateTime.now()).build();
        reviewRepository.save(review);
    }

    @Test
    void findById_Test(){
        // given
        Long id = 1L;
        
        // when
        Optional<Review> review = reviewRepository.findById(id);
        
        // then
        assertNotNull(review);
    }
    
    @Test
    void save_Test() {
        // given
        Long id = 2L;
        Review review = Review.builder().id(id).reviewerId(1L).reviewedId(3L).score(4).comment("리뷰내용2").createdAt(LocalDateTime.now()).build();
        
        // when
        reviewRepository.save(review);
        
        // then
        assertNotNull(reviewRepository.findById(id));
    }

    @Test
    void deleteById_Test(){
        // given
        Long id = 1L;
        
        // when
        reviewRepository.deleteById(id);

        // then
        assertEquals(Optional.empty(), reviewRepository.findById(id));
    }
}
