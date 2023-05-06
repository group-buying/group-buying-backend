package shop.donutmarket.donut.global.dummy;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import shop.donutmarket.donut.domain.review.model.Rate;
import shop.donutmarket.donut.domain.user.model.User;

import java.time.LocalDateTime;

public class DummyEntity {
    public User newUser(String username, Rate rate){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return User.builder()
                .username(username)
                .password(passwordEncoder.encode("1234"))
                .email(username)
                .role("ROLE_USER")
                .rate(rate)
                .ratePoint(20)
                .profile("사진")
                .createdAt(LocalDateTime.now())
                .build();
    }

    public User newAdmin(Long id, String username){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return User.builder()
                .id(id)
                .username(username)
                .password(passwordEncoder.encode("1234"))
                .email(username)
                .role("ROLE_ADMIN")
                .createdAt(LocalDateTime.now())
                .build();
    }
}
