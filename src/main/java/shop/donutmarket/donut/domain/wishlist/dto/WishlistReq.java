package shop.donutmarket.donut.domain.wishlist.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class WishlistReq {
    
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class WishListSaveReqDTO {
        @NotNull
        private Long boardId;
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class WishListDeleteReqDTO {
        @NotNull
        private Long wishlistId;
    }
}
