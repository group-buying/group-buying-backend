package shop.donutmarket.donut.domain.myCategory.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import shop.donutmarket.donut.domain.myCategory.repository.MyCategoryRepository;

@Service
@RequiredArgsConstructor
public class MyCategoryService {
    
    private final MyCategoryRepository myCategoryRepository;
}
