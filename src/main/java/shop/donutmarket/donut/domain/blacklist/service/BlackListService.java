package shop.donutmarket.donut.domain.blacklist.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import shop.donutmarket.donut.domain.blacklist.dto.BlacklistReq;
import shop.donutmarket.donut.domain.blacklist.dto.BlacklistResp;
import shop.donutmarket.donut.domain.blacklist.model.Blacklist;
import shop.donutmarket.donut.domain.blacklist.repository.BlackListRepository;
import shop.donutmarket.donut.domain.user.model.User;
import shop.donutmarket.donut.domain.user.repository.UserRepository;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class BlackListService {

    private final BlackListRepository blackListRepository;

    private final UserRepository userRepository;

    @Transactional
    public BlacklistResp.select 블랙리스트추가(Long userId, Long blockedUserId) {
        try {
            Optional<User> userOP = userRepository.findById(userId);
            Optional<User> blockedUserOP = userRepository.findById(blockedUserId);

            if (userOP.isPresent() && blockedUserOP.isPresent()) {
                User userPS = userOP.get();
                User blockedUserPS = blockedUserOP.get();
                Blacklist blacklist = Blacklist.builder().user(userPS).blockedUser(blockedUserPS).build();
                Blacklist blacklistPS = blackListRepository.save(blacklist);
                BlacklistResp.select resp = new BlacklistResp.select(blacklistPS);
                return resp;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
