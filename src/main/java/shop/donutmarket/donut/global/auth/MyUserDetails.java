package shop.donutmarket.donut.global.auth;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;
import shop.donutmarket.donut.domain.user.model.User;
import shop.donutmarket.donut.global.oauth.NaverUserInfo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

// Authentication 객체에 저장할 수 있는 유일한 타입
@Getter
public class MyUserDetails implements UserDetails, OAuth2User {

    private User user;
    private Map<String, Object> attributes;

    // 일반 시큐리티 로그인 시
    public MyUserDetails(User user) {
        this.user = user;
    }

    // OAuth2.0 로그인시
    public MyUserDetails(User user, Map<String, Object> attributes) {
        this.user = user;
        this.attributes = attributes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(() -> "ROLE_" + user.getRole());
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    // User의 PrimaryKey
    @Override
    public String getName() {
        return user.getId()+"";
    }
}
