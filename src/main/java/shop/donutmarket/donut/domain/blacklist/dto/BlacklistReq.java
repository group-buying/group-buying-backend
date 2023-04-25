package shop.donutmarket.donut.domain.blacklist.dto;

import lombok.Getter;
import lombok.Setter;
import shop.donutmarket.donut.domain.blacklist.model.Blacklist;
import shop.donutmarket.donut.domain.user.model.User;

@Getter
@Setter
public class BlacklistReq {

    @Getter
    @Setter
    public static class insertReq {
        private Long blockedUserId;
    }
}
