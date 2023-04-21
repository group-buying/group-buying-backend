package shop.donutmarket.donut.domain.account.service;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import shop.donutmarket.donut.domain.account.dto.AccountReq;
import shop.donutmarket.donut.domain.account.model.MyAccount;
import shop.donutmarket.donut.domain.account.repository.MyAccountRepository;
import shop.donutmarket.donut.domain.user.model.User;
import shop.donutmarket.donut.domain.user.repository.UserRepository;
import shop.donutmarket.donut.global.auth.MyUserDetails;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MyAccountService {

    private final MyAccountRepository myAccountRepository;
    private final UserRepository userRepository;

    @Transactional
    public Optional<MyAccount> 계좌등록(@AuthenticationPrincipal MyUserDetails myUserDetails, AccountReq.insertDTO insertDTO) {
        Optional<User> userOP = userRepository.findById(myUserDetails.getUser().getId());
        if (userOP.isPresent()) {
            User userPS = userOP.get();
            insertDTO.setUser(userPS);
            myAccountRepository.save(insertDTO.toEntity());
            Optional<MyAccount> myAccount = myAccountRepository.findByUserId(myUserDetails.getUser().getId());
            return myAccount;
        }
        return null;
    }
}
