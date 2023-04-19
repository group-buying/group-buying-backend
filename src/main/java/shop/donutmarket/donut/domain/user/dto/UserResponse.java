package shop.donutmarket.donut.domain.user.dto;

import lombok.Getter;
import lombok.Setter;
import shop.donutmarket.donut.domain.user.model.User;
import shop.donutmarket.donut.global.util.MyDateUtils;

public class UserResponse {
    @Getter
    @Setter
    public static class JoinDTO {
        private Long id;
        private String email;
        private String role;
        private String createdAt; // 포맷이 필요함

        public JoinDTO(User user) {
            this.id = user.getId();
            this.email = user.getEmail();
            this.role = user.getRole();
            this.createdAt = MyDateUtils.toStringFormat(user.getCreatedAt());
        }
    }
}
