package shop.donutmarket.donut.domain.review.controller;

import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import shop.donutmarket.donut.domain.review.service.ReviewService;

@RestController
@RequiredArgsConstructor
public class ReviewController {
    
    private final ReviewService reviewService;
}
