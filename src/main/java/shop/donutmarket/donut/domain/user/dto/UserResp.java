package shop.donutmarket.donut.domain.user.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import shop.donutmarket.donut.domain.user.model.User;

public class UserResp {
    @Getter
    @Setter
    public static class UpdateDTO {
        private String username;
        private String email;
        private String name;
        private String profile;
        private String role;

        public UpdateDTO(String username, String email, String name, String profile, String role) {
            this.username = username;
            this.email = email;
            this.name = name;
            this.profile = profile;
            this.role = role;
        }
    }
}
