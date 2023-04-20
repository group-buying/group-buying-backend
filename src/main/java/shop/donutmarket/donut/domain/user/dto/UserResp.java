package shop.donutmarket.donut.domain.user.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import shop.donutmarket.donut.domain.user.model.User;

public class UserResp {
    @Getter
    @Setter
    public static class UpdateDTO {
        private String email;
        private String password;
        private String name;
        private String profile;
    }
}
