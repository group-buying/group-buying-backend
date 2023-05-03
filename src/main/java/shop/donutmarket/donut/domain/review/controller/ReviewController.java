package shop.donutmarket.donut.domain.review.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import shop.donutmarket.donut.domain.review.dto.ReviewReq.ReviewSaveReqDTO;
import shop.donutmarket.donut.domain.review.dto.ReviewResp.ReviewSaveRespDTO;
import shop.donutmarket.donut.domain.review.service.ReviewService;
import shop.donutmarket.donut.global.auth.MyUserDetails;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reviews")
public class ReviewController {
    
    private final ReviewService reviewService;

    @PostMapping
    public ResponseEntity<?> save(@RequestBody @Valid ReviewSaveReqDTO reviewSaveReqDTO, BindingResult bindingResult, @AuthenticationPrincipal MyUserDetails myUserDetails) {
        ReviewSaveRespDTO saveRespDTO = reviewService.리뷰작성(reviewSaveReqDTO, myUserDetails);
        ResponseDTO<?> responseDTO = new ResponseDTO<>(saveRespDTO);
        return ResponseEntity.ok(responseDTO);
    }
}
