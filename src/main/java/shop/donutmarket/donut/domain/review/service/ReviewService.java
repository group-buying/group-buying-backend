package shop.donutmarket.donut.domain.review.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import shop.donutmarket.donut.domain.review.dto.ReviewReq.ReviewSaveReqDTO;
import shop.donutmarket.donut.domain.review.dto.ReviewResp.ReviewSaveRespDTO;
import shop.donutmarket.donut.domain.review.model.Review;
import shop.donutmarket.donut.domain.review.repository.ReviewRepository;
import shop.donutmarket.donut.domain.user.model.User;
import shop.donutmarket.donut.domain.user.repository.UserRepository;
import shop.donutmarket.donut.global.auth.MyUserDetails;
import shop.donutmarket.donut.global.exception.Exception404;
import shop.donutmarket.donut.global.exception.Exception500;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;

    @Transactional
    public ReviewSaveRespDTO 리뷰작성(ReviewSaveReqDTO reviewSaveReqDTO, @AuthenticationPrincipal MyUserDetails myUserDetails) {
        Optional<User> reviewedUserOP = userRepository.findByIdJoinFetch(reviewSaveReqDTO.getReviewedUserId());

        if (reviewedUserOP.isEmpty()) {
            throw new Exception404("리뷰할 유저를 찾을 수 없습니다");
        }

        try {
            // 평점 변경 1점 추가
            User reviewedUserPS = reviewedUserOP.get();
            reviewedUserPS.rateUp();

            // 리뷰 생성
            Review review = Review.builder().reviewer(myUserDetails.getUser()).reviewed(reviewedUserPS)
                    .score(reviewSaveReqDTO.getScore()).comment(reviewSaveReqDTO.getComment()).createdAt(LocalDateTime.now()).build();

            Review reviewPS = reviewRepository.save(review);

            ReviewSaveRespDTO saveRespDTO = new ReviewSaveRespDTO(reviewPS);
            return saveRespDTO;
        } catch (Exception e) {
            throw new Exception500("리뷰 작성 실패 : " + e.getMessage());
        }
    }
}
