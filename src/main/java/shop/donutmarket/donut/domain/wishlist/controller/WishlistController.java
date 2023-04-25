package shop.donutmarket.donut.domain.wishlist.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
import shop.donutmarket.donut.global.dto.ResponseDTO;


@RestController
@RequiredArgsConstructor
@RequestMapping("/wishlist")
public class WishlistController {
    
    private final WishlistService wishlistService;

    @GetMapping
    public ResponseEntity<?> wishlist(@AuthenticationPrincipal MyUserDetails myUserDetails) {
        List<MyWishListRespDTO> myRespDTO =  wishlistService.내관심목록(myUserDetails);
        return new ResponseEntity<>(new ResponseDTO<>().data(myRespDTO), HttpStatus.CREATED);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody @Valid WishListSaveReqDTO wishListSaveReqDTO, @AuthenticationPrincipal MyUserDetails myUserDetails) {
        WishListSaveRespDTO saveRespDTO =  wishlistService.관심등록(wishListSaveReqDTO, myUserDetails);
        return new ResponseEntity<>(new ResponseDTO<>().data(saveRespDTO), HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestBody @Valid WishListDeleteReqDTO wishListDeleteReqDTO, @AuthenticationPrincipal MyUserDetails myUserDetails) {
        wishlistService.관심등록제거(wishListDeleteReqDTO, myUserDetails);
        return new ResponseEntity<>(new ResponseDTO<>(), HttpStatus.OK);
    }
    
}
