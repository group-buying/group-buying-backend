package shop.donutmarket.donut.domain.wishlist.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import shop.donutmarket.donut.domain.wishlist.dto.WishlistReq.WishListDeleteReqDTO;
import shop.donutmarket.donut.domain.wishlist.dto.WishlistReq.WishListSaveReqDTO;
import shop.donutmarket.donut.domain.wishlist.dto.WishlistResp.MyWishListRespDTO;
import shop.donutmarket.donut.domain.wishlist.dto.WishlistResp.WishListSaveRespDTO;
import shop.donutmarket.donut.domain.wishlist.service.WishlistService;
import shop.donutmarket.donut.global.auth.MyUserDetails;


@RestController
@RequiredArgsConstructor
@RequestMapping("/wishlist")
public class WishlistController {
    
    private final WishlistService wishlistService;

    @GetMapping
    public ResponseEntity<?> wishlist(@AuthenticationPrincipal MyUserDetails myUserDetails) {
        MyWishListRespDTO myRespDTO =  wishlistService.내관심목록(myUserDetails);
        return ResponseEntity.ok(myRespDTO);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody @Valid WishListSaveReqDTO wishListSaveReqDTO, BindingResult bindingResult, @AuthenticationPrincipal MyUserDetails myUserDetails) {
        WishListSaveRespDTO saveRespDTO =  wishlistService.관심등록(wishListSaveReqDTO, myUserDetails);
        return ResponseEntity.ok(saveRespDTO);
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestBody @Valid WishListDeleteReqDTO wishListDeleteReqDTO, BindingResult bindingResult, @AuthenticationPrincipal MyUserDetails myUserDetails) {
        wishlistService.관심등록제거(wishListDeleteReqDTO, myUserDetails);
        return ResponseEntity.ok("위시리스트 삭제 성공");
    }
    
}
