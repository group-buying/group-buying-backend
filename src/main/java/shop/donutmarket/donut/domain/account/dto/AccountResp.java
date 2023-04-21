package shop.donutmarket.donut.domain.account.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import shop.donutmarket.donut.domain.account.model.MyAccount;
import shop.donutmarket.donut.domain.user.model.User;

public class AccountResp {
    @Getter
    @Setter
    public static class insertDTO {
        private Long id;
        private Long userId;
        private String brand;
        private String accountNumber;
    }
}
