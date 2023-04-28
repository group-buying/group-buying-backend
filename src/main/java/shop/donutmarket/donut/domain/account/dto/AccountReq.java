package shop.donutmarket.donut.domain.account.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import shop.donutmarket.donut.domain.account.model.MyAccount;
import shop.donutmarket.donut.domain.user.model.User;

import java.util.Optional;

public class AccountReq {

    @Getter
    @Setter
    public static class insertDTO {
        private Long userId;
        @NotBlank
        private String brand;
        @NotBlank
        private String accountNumber;

        public MyAccount toEntity(User user) {
            return MyAccount.builder().user(user).brand(brand).accountNumber(accountNumber).build();
        }
    }

    @Getter
    @Setter
    public static class updateDTO {
        private String brand;
        private String accountNumber;
    }
}
