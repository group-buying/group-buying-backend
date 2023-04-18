package shop.donutmarket.donut.domain.account.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import shop.donutmarket.donut.domain.account.repository.MyAccountRepository;

@Service
@RequiredArgsConstructor
public class MyAccountService {
    
    private final MyAccountRepository myAccountRepository;
}
