package shop.donutmarket.donut.domain.wishlist.controller;

import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import shop.donutmarket.donut.domain.wishlist.service.WishlistService;

@RestController
@RequiredArgsConstructor
public class WishlistController {
    
    private final WishlistService wishlistService;
}
