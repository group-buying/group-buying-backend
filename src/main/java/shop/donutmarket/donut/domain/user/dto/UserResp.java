package shop.donutmarket.donut.domain.user.dto;

import lombok.Getter;
import lombok.Setter;
import shop.donutmarket.donut.domain.user.model.User;

public class UserResp {
    @Getter
    @Setter
    public static class UpdateDTO {
        private String username;
        private String email;
        private String profile;
        private String role;

        public UpdateDTO(String username, String email, String profile, String role) {
            this.username = username;
            this.email = email;
            this.profile = profile;
            this.role = role;
        }
    }

    @Getter
    @Setter
    public static class JoinDTO {
        private User user;

        public JoinDTO(User user) {
            this.user = user;
        }
    }

    @Getter
    @Setter
    public static class LoginDTO {
        private User user;

        public LoginDTO(User user) {
            this.user = user;
        }
    }

    @Getter
    @Setter
    public static class JwtUserDTO {
        private User user;

        public JwtUserDTO(User user) {
            this.user = user;
        }
    }
}
