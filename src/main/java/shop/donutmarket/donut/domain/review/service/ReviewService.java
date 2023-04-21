package shop.donutmarket.donut.domain.review.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import shop.donutmarket.donut.domain.board.repository.BoardRepository;
import shop.donutmarket.donut.domain.review.dto.ReviewReq.ReviewSaveReqDTO;
import shop.donutmarket.donut.domain.review.dto.ReviewResp.ReviewSaveRespDTO;
import shop.donutmarket.donut.domain.review.model.Rate;
import shop.donutmarket.donut.domain.review.model.Review;
import shop.donutmarket.donut.domain.review.repository.RateRepository;
import shop.donutmarket.donut.domain.review.repository.ReviewRepository;
import shop.donutmarket.donut.domain.user.model.User;
import shop.donutmarket.donut.global.auth.MyUserDetails;

@Service
@RequiredArgsConstructor
public class ReviewService {
    
    private final ReviewRepository reviewRepository;
    private final RateRepository rateRepository;

    public List<Review> 내리뷰목록(@AuthenticationPrincipal MyUserDetails myUserDetails) {
        List<Review> myReviews = reviewRepository.findAllByReviewerId(myUserDetails.getUser().getId());

        List<Review> reviewList = new ArrayList<>();
        for (Review reviewPS : myReviews) {
            User reviewedUser = User.builder().name(reviewPS.getReviewed().getName()).build();
            Review review = Review.builder().id(reviewPS.getId()).reviewed(reviewedUser).comment(reviewPS.getComment())
            .score(reviewPS.getScore()).createdAt(reviewPS.getCreatedAt()).build();
            reviewList.add(review);
        }
        return reviewList;
    }

    @Transactional
    public ReviewSaveRespDTO 리뷰작성(ReviewSaveReqDTO reviewSaveReqDTO, @AuthenticationPrincipal MyUserDetails myUserDetails) {

        // 리뷰 생성
        Review review = Review.builder().reviewer(myUserDetails.getUser()).reviewed(reviewSaveReqDTO.getReviewedUser())
        .score(reviewSaveReqDTO.getScore()).comment(reviewSaveReqDTO.getComment()).createdAt(LocalDateTime.now()).build();

        Review reviewPS = reviewRepository.save(review);

        // 평점 변경 1점 추가
        Optional<Rate> reviewedUserRateOP = rateRepository.findByUserId(reviewSaveReqDTO.getReviewedUser().getId());

        Rate rate = reviewedUserRateOP.get();
        rate.rateUp();

        ReviewSaveRespDTO saveRespDTO = new ReviewSaveRespDTO(reviewPS.getScore(), reviewPS.getComment());
        return saveRespDTO;
    }

}
