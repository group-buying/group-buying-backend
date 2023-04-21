package shop.donutmarket.donut.domain.account.service;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import shop.donutmarket.donut.domain.account.dto.AccountReq;
import shop.donutmarket.donut.domain.account.dto.AccountResp;
import shop.donutmarket.donut.domain.account.model.MyAccount;
import shop.donutmarket.donut.domain.account.repository.MyAccountRepository;
import shop.donutmarket.donut.domain.user.model.User;
import shop.donutmarket.donut.domain.user.repository.UserRepository;
import shop.donutmarket.donut.global.auth.MyUserDetails;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MyAccountService {

    private final MyAccountRepository myAccountRepository;
    private final UserRepository userRepository;

    @Transactional
    public AccountResp.insertDTO 계좌등록(Long userId, AccountReq.insertDTO insertDTO) {
        Optional<User> userOP = userRepository.findById(userId);
        if (userOP.isPresent()) {
            User userPS = userOP.get();
            insertDTO.setUser(userPS);
            myAccountRepository.save(insertDTO.toEntity());
            Optional<MyAccount> myAccountOP = myAccountRepository.findByUserId(userId);
            if (myAccountOP.isPresent()) {
                MyAccount myAccountPS = myAccountOP.get();
                AccountResp.insertDTO resp = new AccountResp.insertDTO();
                resp.setId(myAccountPS.getId());
                resp.setUserId(myAccountPS.getUser().getId());
                resp.setBrand(myAccountPS.getBrand());
                resp.setAccountNumber(myAccountPS.getAccountNumber());
                return resp;
            }
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
        return -1;
    }

    @Transactional
    public AccountResp.updateDTO 계좌수정(Long userId, AccountReq.updateDTO updateDTO) {
        Optional<MyAccount> myAccountOP = myAccountRepository.findByUserId(userId);
        if (myAccountOP.isPresent()) {
            MyAccount myAccountPS = myAccountOP.get();
            myAccountPS.updateMyAccount(updateDTO.getBrand(), updateDTO.getAccountNumber());
            AccountResp.updateDTO resp = new AccountResp.updateDTO();
            resp.setId(myAccountPS.getId());
            resp.setUserId(myAccountPS.getUser().getId());
            resp.setBrand(myAccountPS.getBrand());
            resp.setAccountNumber(myAccountPS.getAccountNumber());
            return resp;
        }
        return null;
    }

    @Transactional(readOnly = true)
    public AccountResp.selectDTO 계좌조회(Long userId) {
        Optional<MyAccount> myAccountOP = myAccountRepository.findByUserId(userId);
        if (myAccountOP.isPresent()) {
            MyAccount myAccountPS = myAccountOP.get();
            AccountResp.selectDTO resp = new AccountResp.selectDTO();
            resp.setId(myAccountPS.getId());
            resp.setUserId(myAccountPS.getUser().getId());
            resp.setBrand(myAccountPS.getBrand());
            resp.setAccountNumber(myAccountPS.getAccountNumber());
            return resp;
        }
        return null;
    }
}
