package shop.donutmarket.donut.domain.wishlist.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class WishlistResp {
    
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class WishListSaveRespDTO {
        private String title;
        private String state;
        private String city;
        private LocalDateTime createdAt;
    }
    
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MyWishListRespDTO {
        private Long id;
        private String title;
        private String organizer;
        private String state;
        private String city;
        private LocalDateTime createdAt;
    }
}
