package shop.donutmarket.donut.domain.blacklist.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import shop.donutmarket.donut.domain.blacklist.repository.BlackListRepository;


@Service
@RequiredArgsConstructor
public class BlackListService {
    
    private final BlackListRepository blackListRepository;
}
