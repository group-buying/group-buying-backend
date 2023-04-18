package shop.donutmarket.donut.domain.review.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import shop.donutmarket.donut.domain.review.repository.RateRepository;

@Service
@RequiredArgsConstructor
public class RateService {
    
    private final RateRepository rateRepository;
}
