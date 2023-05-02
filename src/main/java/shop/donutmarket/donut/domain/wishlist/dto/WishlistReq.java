package shop.donutmarket.donut.domain.wishlist.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class WishlistReq {
    
    @Getter
    @Setter
    public static class WishListSaveReqDTO {
        @NotNull
        private Long boardId;
    }

    @Getter
    @Setter
    public static class WishListDeleteReqDTO {
        @NotNull
        private Long wishlistId;
    }
}
