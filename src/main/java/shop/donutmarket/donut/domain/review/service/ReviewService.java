package shop.donutmarket.donut.domain.review.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import shop.donutmarket.donut.domain.review.model.Review;
import shop.donutmarket.donut.domain.review.repository.ReviewRepository;

@Service
@RequiredArgsConstructor
public class ReviewService {
    
    private final ReviewRepository reviewRepository;

    public List<Review> 내리뷰목록(Long id) {
        List<Review> myReviews = reviewRepository.findAllByUserId(id);
        return myReviews;
    }


}
