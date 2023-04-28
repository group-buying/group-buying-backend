package shop.donutmarket.donut.domain.user.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

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

    @Getter
    @Setter
    @AllArgsConstructor
    public static class AdminSearchUserDTO {
        private Long id;
        private String name;
        private String ratename;
        private String status;
        private LocalDateTime createdAt;
    }
}
