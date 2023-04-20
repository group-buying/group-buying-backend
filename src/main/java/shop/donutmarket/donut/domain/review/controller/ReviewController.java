package shop.donutmarket.donut.domain.review.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import shop.donutmarket.donut.domain.review.dto.ReviewReq.ReviewSaveReqDTO;
import shop.donutmarket.donut.domain.review.model.Review;
import shop.donutmarket.donut.domain.review.service.ReviewService;
import shop.donutmarket.donut.global.dto.ResponseDTO;

@RestController
@RequiredArgsConstructor
public class ReviewController {
    
    private final ReviewService reviewService;

    @GetMapping("/reviews")
    public @ResponseBody ResponseEntity<?> myReviews(Long id) {
        List<Review> reviewList = reviewService.내리뷰목록(id);
        return new ResponseEntity<>(new ResponseDTO<>().data(reviewList), HttpStatus.OK);
    }

    @PostMapping("/reviews")
    public @ResponseBody ResponseEntity<?> save(ReviewSaveReqDTO reviewSaveReqDTO) {
        reviewService.리뷰작성(reviewSaveReqDTO);
        return new ResponseEntity<>(new ResponseDTO<>().data(null), HttpStatus.CREATED);
    }
}
