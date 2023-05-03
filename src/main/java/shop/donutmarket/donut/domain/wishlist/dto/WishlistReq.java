package shop.donutmarket.donut.domain.wishlist.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

public class WishlistReq {
    
    @Getter
    @Setter
    public static class WishListSaveReqDTO {
        @NotNull(message = "등록하려는 게시글ID를 입력해주세요.")
        private Long boardId;
    }

    @Getter
    @Setter
    public static class WishListDeleteReqDTO {
        @NotNull(message = "제거하려는 위시리스트ID를 입력해주세요.")
        private Long wishlistId;
    }
}
