package shop.donutmarket.donut.domain.main.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import shop.donutmarket.donut.domain.main.dto.MainResp;
import shop.donutmarket.donut.domain.main.service.MainService;
import shop.donutmarket.donut.global.auth.MyUserDetails;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MainController {

    private final MainService mainService;

    @GetMapping("/main")
    public ResponseEntity<?> main(@AuthenticationPrincipal MyUserDetails myUserDetails) {
        MainResp mainResp = mainService.게시글목록보기(myUserDetails.getUser().getId());
        return ResponseEntity.ok().body(mainResp);
    }
}
