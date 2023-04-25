package shop.donutmarket.donut.domain.blacklist.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import shop.donutmarket.donut.domain.blacklist.dto.BlacklistReq;
import shop.donutmarket.donut.domain.blacklist.dto.BlacklistResp;
import shop.donutmarket.donut.domain.blacklist.service.BlackListService;
import shop.donutmarket.donut.global.auth.MyUserDetails;


@RestController
@RequiredArgsConstructor
public class BlackListController {
    
    private final BlackListService blackListService;

    @PostMapping("/blacklists/{id}")
    public ResponseEntity<?> insert(@PathVariable Long id,
                                    @AuthenticationPrincipal MyUserDetails myUserDetails) {
        BlacklistResp.select resp = blackListService.블랙리스트추가(myUserDetails.getUser().getId(), id);
        return ResponseEntity.ok().body(resp);
    }
}
