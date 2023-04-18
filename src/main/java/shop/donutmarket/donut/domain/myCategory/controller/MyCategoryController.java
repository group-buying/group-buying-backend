package shop.donutmarket.donut.domain.myCategory.controller;

import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import shop.donutmarket.donut.domain.myCategory.service.MyCategoryService;

@RestController
@RequiredArgsConstructor
public class MyCategoryController {
    
    private final MyCategoryService myCategoryService;
    
}
