package shop.donutmarket.donut.domain.blacklist.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BlacklistReq {

    @Getter
    @Setter
    public static class insertReq {
        private Long blockedUserId;
    }
}
