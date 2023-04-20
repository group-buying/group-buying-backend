package shop.donutmarket.donut.domain.review.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import shop.donutmarket.donut.domain.board.repository.BoardRepository;
import shop.donutmarket.donut.domain.review.dto.ReviewReq.ReviewSaveReqDTO;
import shop.donutmarket.donut.domain.review.model.Rate;
import shop.donutmarket.donut.domain.review.model.Review;
import shop.donutmarket.donut.domain.review.repository.RateRepository;
import shop.donutmarket.donut.domain.review.repository.ReviewRepository;
import shop.donutmarket.donut.domain.user.model.User;

@Service
@RequiredArgsConstructor
public class ReviewService {
    
    private final ReviewRepository reviewRepository;
    private final BoardRepository boardRepository;
    private final RateRepository rateRepository;

    public List<Review> 내리뷰목록(Long id) {
        List<Review> myReviews = reviewRepository.findAllByUserId(id);
        return myReviews;
    }

    @Transactional
    public void 리뷰작성(ReviewSaveReqDTO reviewSaveReqDTO) {
        User reviewedUser = boardRepository.findById(reviewSaveReqDTO.getBoardId()).get().getOrganizer();

        // 리뷰 생성
        Review review = Review.builder().reviewer(reviewSaveReqDTO.getReviewer()).reviewed(reviewedUser)
        .score(reviewSaveReqDTO.getScore()).comment(reviewSaveReqDTO.getComment()).createdAt(LocalDateTime.now()).build();

        reviewRepository.save(review);

        // 평점 변경 1점 추가
        Rate rate = rateRepository.findByUserId(reviewedUser.getId()).get();
        Rate changeRate = Rate.builder().id(rate.getId()).ratePoint(rate.getRatePoint()+1).build();

        rateRepository.save(changeRate);
        
    }
}
