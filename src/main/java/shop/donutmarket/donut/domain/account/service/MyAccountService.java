package shop.donutmarket.donut.domain.account.service;

import com.sun.source.tree.TryTree;
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
import shop.donutmarket.donut.global.exception.Exception401;
import shop.donutmarket.donut.global.exception.Exception500;

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
        if (userOP.isEmpty()) {
            throw new Exception401("존재하지 않는 유저입니다");
        }
        try {
            User userPS = userOP.get();
            insertDTO.setUser(userPS);
            myAccountRepository.save(insertDTO.toEntity());

            // 영속성 컨텍스트에 존재
            Optional<MyAccount> myAccountOP = myAccountRepository.findByUserId(userId);
            MyAccount myAccountPS = myAccountOP.get();
            AccountResp.insertDTO resp = new AccountResp.insertDTO();
            resp.setId(myAccountPS.getId());
            resp.setUserId(myAccountPS.getUser().getId());
            resp.setBrand(myAccountPS.getBrand());
            resp.setAccountNumber(myAccountPS.getAccountNumber());
            return resp;
        } catch (Exception e) {
            throw new Exception500("계좌등록 실패 : " + e.getMessage());
        }
    }

    @Transactional
    public void 계좌삭제(Long userId) {
        Optional<MyAccount> myAccountOP = myAccountRepository.findByUserId(userId);
        if (myAccountOP.isEmpty()) {
            throw new Exception401("존재하지 않는 유저입니다");
        }
        try {
            MyAccount myAccountPS = myAccountOP.get();
            myAccountRepository.delete(myAccountPS);
        } catch (Exception e) {
            throw new Exception500("계좌삭제 실패 : " + e.getMessage());
        }
    }

    @Transactional
    public AccountResp.updateDTO 계좌수정(Long userId, AccountReq.updateDTO updateDTO) {
        Optional<MyAccount> myAccountOP = myAccountRepository.findByUserId(userId);
        if (myAccountOP.isEmpty()) {
            throw new Exception401("존재하지 않는 유저입니다");
        }
        try {
            MyAccount myAccountPS = myAccountOP.get();
            myAccountPS.updateMyAccount(updateDTO.getBrand(), updateDTO.getAccountNumber());
            AccountResp.updateDTO resp = new AccountResp.updateDTO();
            resp.setId(myAccountPS.getId());
            resp.setUserId(myAccountPS.getUser().getId());
            resp.setBrand(myAccountPS.getBrand());
            resp.setAccountNumber(myAccountPS.getAccountNumber());
            return resp;
        } catch (Exception e) {
            throw new Exception500("계좌수정 실패 : " + e.getMessage());
        }
    }

    @Transactional(readOnly = true)
    public AccountResp.selectDTO 계좌조회(Long userId) {
        Optional<MyAccount> myAccountOP = myAccountRepository.findByUserId(userId);
        if (myAccountOP.isEmpty()) {
            throw new Exception401("존재하지 않는 유저입니다");
        }
        try {
            MyAccount myAccountPS = myAccountOP.get();
            AccountResp.selectDTO resp = new AccountResp.selectDTO();
            resp.setId(myAccountPS.getId());
            resp.setUserId(myAccountPS.getUser().getId());
            resp.setBrand(myAccountPS.getBrand());
            resp.setAccountNumber(myAccountPS.getAccountNumber());
            return resp;
        } catch (Exception e) {
            throw new Exception500("계좌조회 실패 : " + e.getMessage());
        }
    }
}
