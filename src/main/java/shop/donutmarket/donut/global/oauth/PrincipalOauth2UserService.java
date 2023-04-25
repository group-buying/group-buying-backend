package shop.donutmarket.donut.global.oauth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import shop.donutmarket.donut.domain.user.model.User;
import shop.donutmarket.donut.domain.user.repository.UserRepository;
import shop.donutmarket.donut.global.auth.MyUserDetails;

import java.util.Map;
import java.util.Optional;

@Service
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {
    @Autowired
    private UserRepository userRepository;

    // userRequest 는 code를 받아서 accessToken을 응답 받은 객체
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest); // google의 회원 프로필 조회

        // code를 통해 구성한 정보
        System.out.println("userRequest clientRegistration : " + userRequest.getClientRegistration());
        // token을 통해 응답받은 회원정보
        System.out.println("oAuth2User : " + oAuth2User);

        return processOAuth2User(userRequest, oAuth2User);
    }

    private OAuth2User processOAuth2User(OAuth2UserRequest userRequest, OAuth2User oAuth2User) {

        NaverUserInfo naverUserInfo = null;
        if (userRequest.getClientRegistration().getRegistrationId().equals("naver")){
            naverUserInfo = new NaverUserInfo((Map)oAuth2User.getAttributes().get("response"));
        } else {

        }

        Optional<User> userOptional =
                userRepository.findByProviderAndProviderId(naverUserInfo.getProvider(), naverUserInfo.getProviderId());

        User user;
        if (userOptional.isPresent()) {
            user = userOptional.get();
            // user가 존재하면 update 해주기
            user.updateEmail(naverUserInfo.getEmail());
            userRepository.save(user);
        } else {
            // user의 패스워드가 null이기 때문에 OAuth 유저는 일반적인 로그인을 할 수 없음.
            user = User.builder()
                    .username(naverUserInfo.getProvider() + "_" + naverUserInfo.getProviderId())
                    .email(naverUserInfo.getEmail())
                    .role("ROLE_USER")
                    .provider(naverUserInfo.getProvider())
                    .providerId(naverUserInfo.getProviderId())
                    .build();

            userRepository.save(user);
        }

        return new MyUserDetails(user, naverUserInfo.getAttributes());
    }
}
