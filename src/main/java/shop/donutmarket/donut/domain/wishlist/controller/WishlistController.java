package shop.donutmarket.donut.domain.wishlist.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import shop.donutmarket.donut.domain.wishlist.dto.WishlistReq.WishListSaveReqDTO;
import shop.donutmarket.donut.domain.wishlist.dto.WishlistResp.WishListSaveRespDTO;
import shop.donutmarket.donut.domain.wishlist.service.WishlistService;
import shop.donutmarket.donut.global.auth.MyUserDetails;
import shop.donutmarket.donut.global.dto.ResponseDTO;


@RestController
@RequiredArgsConstructor
@RequestMapping("/wishlist")
public class WishlistController {
    
    private final WishlistService wishlistService;

    @PostMapping
    public ResponseEntity<?> save(@RequestBody WishListSaveReqDTO wishListSaveReqDTO, @AuthenticationPrincipal MyUserDetails myUserDetails) {
        WishListSaveRespDTO saveRespDTO =  wishlistService.관심등록(wishListSaveReqDTO, myUserDetails);
        return new ResponseEntity<>(new ResponseDTO<>().data(saveRespDTO), HttpStatus.CREATED);
    }
    
}
