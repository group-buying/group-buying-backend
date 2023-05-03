package shop.donutmarket.donut.domain.wishlist.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import shop.donutmarket.donut.domain.wishlist.model.Wishlist;

public class WishlistResp {
    
    @Getter
    @Setter
    public static class WishListSaveRespDTO {
        private Wishlist wishlist;

        public WishListSaveRespDTO(Wishlist wishlist) {
            this.wishlist = wishlist;
        }
    }
    
    @Getter
    @Setter
    public static class MyWishListRespDTO {
        private List<Wishlist> wishlistList;

        public MyWishListRespDTO(List<Wishlist> wishlistList) {
            this.wishlistList = wishlistList;
        }
    }
}
