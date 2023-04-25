package shop.donutmarket.donut.domain.blacklist.dto;

import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import shop.donutmarket.donut.domain.blacklist.model.Blacklist;
import shop.donutmarket.donut.domain.user.model.User;

import java.time.LocalDateTime;

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
