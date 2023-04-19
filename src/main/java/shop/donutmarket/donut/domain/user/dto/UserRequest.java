package shop.donutmarket.donut.domain.user.dto;

import lombok.Getter;
import lombok.Setter;
import shop.donutmarket.donut.domain.user.model.User;

public class UserRequest {
    @Getter
    @Setter
    public static class JoinDTO {
        private String email;
        private String password;
        private String role;

        public User toEntity() {
            return User.builder().email(email).password(password).role(role).build();
        }
    }

    @Getter
    @Setter
    public static class LoginDTO {
        private String email;
        private String password;
    }
}
