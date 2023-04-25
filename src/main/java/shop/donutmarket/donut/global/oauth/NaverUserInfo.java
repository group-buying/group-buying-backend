package shop.donutmarket.donut.global.oauth;

import java.util.Map;

public class NaverUserInfo {

    private Map<String, Object> attributes;

    public NaverUserInfo(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    // 리소스 서버로부터 받는 회원정보
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public String getProviderId() {
        return (String) attributes.get("id");
    }

    public String getName() {
        return (String) attributes.get("name");
    }

    public String getEmail() {
        return (String) attributes.get("email");
    }

    public String getProvider() {
        return "naver";
    }
}
