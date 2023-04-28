package shop.donutmarket.donut.global.dummy;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import shop.donutmarket.donut.domain.user.model.User;

import java.time.LocalDateTime;

public class DummyEntity {
    public User newUser(String username, String name){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return User.builder()
                .username(username)
                .password(passwordEncoder.encode("1234"))
                .name(name)
                .email(username+"@nate.com")
                .role("ROLE_USER")
                .profile("그림")
                .createdAt(LocalDateTime.now())
                .build();
    }

    public User newAdmin(Long id, String username, String name){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return User.builder()
                .id(id)
                .username(username)
                .password(passwordEncoder.encode("1234"))
                .name(name)
                .email(username+"@nate.com")
                .role("ROLE_ADMIN")
                .createdAt(LocalDateTime.now())
                .build();
    }
}
