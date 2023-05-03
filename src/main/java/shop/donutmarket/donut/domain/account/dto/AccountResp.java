package shop.donutmarket.donut.domain.account.dto;

import lombok.Getter;
import lombok.Setter;

public class AccountResp {
    @Getter
    @Setter
    public static class insertDTO {
        private Long id;
        private Long userId;
        private String brand;
        private String accountNumber;
    }
    @Getter
    @Setter
    public static class updateDTO {
        private Long id;
        private Long userId;
        private String brand;
        private String accountNumber;
    }

    @Getter
    @Setter
    public static class selectDTO {
        private Long id;
        private Long userId;
        private String brand;
        private String accountNumber;
    }
}
