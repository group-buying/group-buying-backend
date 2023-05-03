package shop.donutmarket.donut.domain.blacklist.dto;

import lombok.Getter;
import lombok.Setter;
import shop.donutmarket.donut.domain.blacklist.model.Blacklist;

@Getter
@Setter
public class BlacklistResp {

    @Getter
    @Setter
    public static class select{
        private Blacklist blacklist;

        public select(Blacklist blacklist) {
            this.blacklist = blacklist;
        }
    }
}
