package shop.donutmarket.donut.domain.review.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import shop.donutmarket.donut.domain.review.dto.ReviewReq.ReviewSaveReqDTO;
import shop.donutmarket.donut.domain.review.dto.ReviewResp.ReviewSaveRespDTO;
import shop.donutmarket.donut.domain.review.model.Rate;
import shop.donutmarket.donut.domain.review.model.Review;
import shop.donutmarket.donut.domain.review.repository.RateRepository;
import shop.donutmarket.donut.domain.review.repository.ReviewRepository;
import shop.donutmarket.donut.domain.user.model.User;
import shop.donutmarket.donut.global.auth.MyUserDetails;
import shop.donutmarket.donut.global.exception.Exception404;
import shop.donutmarket.donut.global.exception.Exception500;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final RateRepository rateRepository;

    @Transactional
    public ReviewSaveRespDTO 리뷰작성(ReviewSaveReqDTO reviewSaveReqDTO, @AuthenticationPrincipal MyUserDetails myUserDetails) {
        Optional<Rate> reviewedUserRateOP = rateRepository.findByUserId(reviewSaveReqDTO.getReviewedUser().getId());

        if (reviewedUserRateOP.isEmpty()) {
            throw new Exception404("유저 등급을 찾을 수 없습니다");
        }

        try {
            // 평점 변경 1점 추가
            Rate rate = reviewedUserRateOP.get();
            rate.rateUp();

            // 리뷰 생성
            Review review = Review.builder().reviewer(myUserDetails.getUser()).reviewed(reviewSaveReqDTO.getReviewedUser())
                    .score(reviewSaveReqDTO.getScore()).comment(reviewSaveReqDTO.getComment()).createdAt(LocalDateTime.now()).build();

            Review reviewPS = reviewRepository.save(review);

            ReviewSaveRespDTO saveRespDTO = new ReviewSaveRespDTO(reviewPS.getScore(), reviewPS.getComment());
            return saveRespDTO;
        } catch (Exception e) {
            throw new Exception500("리뷰 작성 실패 : " + e.getMessage());
        }
    }
}
