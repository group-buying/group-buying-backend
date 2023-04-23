package shop.donutmarket.donut.domain.review.controller;

import java.util.List;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import shop.donutmarket.donut.domain.review.dto.ReviewReq.ReviewSaveReqDTO;
import shop.donutmarket.donut.domain.review.dto.ReviewResp.ReviewSaveRespDTO;
import shop.donutmarket.donut.domain.review.model.Review;
import shop.donutmarket.donut.domain.review.service.ReviewService;
import shop.donutmarket.donut.global.auth.MyUserDetails;
import shop.donutmarket.donut.global.dto.ResponseDto;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reviews")
public class ReviewController {
    
    private final ReviewService reviewService;

    @GetMapping
    public ResponseEntity<?> myReviews(@AuthenticationPrincipal MyUserDetails myUserDetails) {
        List<Review> reviewList = reviewService.내리뷰목록(myUserDetails);
        return new ResponseEntity<>(new ResponseDto<>().data(reviewList), HttpStatus.OK);
    }

<<<<<<< HEAD
    @PostMapping
    public ResponseEntity<?> save(@RequestBody @Valid ReviewSaveReqDTO reviewSaveReqDTO, @AuthenticationPrincipal MyUserDetails myUserDetails) {
        ReviewSaveRespDTO saveRespDTO = reviewService.리뷰작성(reviewSaveReqDTO, myUserDetails);
=======
    @PostMapping("/reviews")
    public @ResponseBody ResponseEntity<?> save(@RequestBody @Valid ReviewSaveReqDTO reviewSaveReqDTO) {
        ReviewSaveRespDTO saveRespDTO = reviewService.리뷰작성(reviewSaveReqDTO);
>>>>>>> 45375e1 (Feat: PaymentInfoReq, PaymentInfoRepository 생성)
        return new ResponseEntity<>(new ResponseDto<>().data(saveRespDTO), HttpStatus.CREATED);
    }
}
