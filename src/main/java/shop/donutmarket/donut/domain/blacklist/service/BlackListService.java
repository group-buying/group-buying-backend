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
import shop.donutmarket.donut.global.exception.Exception401;
import shop.donutmarket.donut.global.exception.Exception500;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class BlackListService {

    private final BlackListRepository blackListRepository;

    private final UserRepository userRepository;

    @Transactional
    public BlacklistResp.select 블랙리스트추가(Long userId, Long blockedUserId) {
        Optional<User> userOP = userRepository.findById(userId);
        Optional<User> blockedUserOP = userRepository.findById(blockedUserId);
        if (userOP.isEmpty() || blockedUserOP.isEmpty()) {
            throw new Exception401("존재하지 않는 유저입니다");
        }
        try {
            User userPS = userOP.get();
            User blockedUserPS = blockedUserOP.get();
            Blacklist blacklist = Blacklist.builder().user(userPS).blockedUser(blockedUserPS).build();
            Blacklist blacklistPS = blackListRepository.save(blacklist);
            BlacklistResp.select resp = new BlacklistResp.select(blacklistPS);
            return resp;
        } catch (Exception e) {
            throw new Exception500("블랙리스트추가 실패 : " + e.getMessage());
        }
    }

    @Transactional
    public void 블랙리스트삭제(Long blacklistId, Long userId) {
        Optional<User> userOP = userRepository.findById(userId);
        Optional<Blacklist> blacklistOP = blackListRepository.findByIdWithUserId(blacklistId, userId);

        if (userOP.isEmpty() || blacklistOP.isEmpty()) {
            throw new Exception401("존재하지 않는 유저입니다");
        }
        try {
            Blacklist blacklistPS = blacklistOP.get();
            blackListRepository.delete(blacklistPS);
        } catch (Exception e) {
            throw new Exception500("블랙리스트삭제 실패 : " + e.getMessage());
        }
    }
}
