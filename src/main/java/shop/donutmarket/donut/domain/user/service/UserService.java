package shop.donutmarket.donut.domain.user.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import shop.donutmarket.donut.domain.user.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {
    
    private final UserRepository userRepository;
    
}
