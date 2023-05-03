package shop.donutmarket.donut.domain.account.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import shop.donutmarket.donut.domain.account.model.MyAccount;
import shop.donutmarket.donut.domain.user.model.User;

public class AccountReq {

    @Getter
    @Setter
    public static class insertDTO {
        private Long userId;
        @NotBlank(message = "은행사를 입력해주세요")
        private String brand;
        @NotBlank(message = "계좌번호를 입력해주세요")
        private String accountNumber;
        
        public MyAccount toEntity(User user) {
            return MyAccount.builder().user(user).brand(brand).accountNumber(accountNumber).build();
        }
    }
    
    @Getter
    @Setter
    public static class updateDTO {
        @NotBlank(message = "은행사를 입력해주세요")
        private String brand;
        @NotBlank(message = "계좌번호를 입력해주세요")
        private String accountNumber;
    }
}
