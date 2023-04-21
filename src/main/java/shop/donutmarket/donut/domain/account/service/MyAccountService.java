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
    public Optional<MyAccount> 계좌등록(Long userId, AccountReq.insertDTO insertDTO) {
        Optional<User> userOP = userRepository.findById(userId);
        if (userOP.isPresent()) {
            User userPS = userOP.get();
            insertDTO.setUser(userPS);
            myAccountRepository.save(insertDTO.toEntity());
            Optional<MyAccount> myAccount = myAccountRepository.findByUserId(userId);
            return myAccount;
        }
        return null;
    }

    @Transactional
    public int 계좌삭제(Long userId) {
        Optional<MyAccount> myAccountOP = myAccountRepository.findByUserId(userId);
        if (myAccountOP.isPresent()) {
            MyAccount myAccountPS = myAccountOP.get();
            myAccountRepository.delete(myAccountPS);
            return 1;
        }
        return  -1;
    }
}
