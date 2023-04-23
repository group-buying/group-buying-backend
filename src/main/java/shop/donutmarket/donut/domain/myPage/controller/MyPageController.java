package shop.donutmarket.donut.domain.myPage.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import shop.donutmarket.donut.domain.myPage.service.MyPageService;
import shop.donutmarket.donut.global.auth.MyUserDetails;

@RestController
@RequiredArgsConstructor
public class MyPageController {

    private final MyPageService myPageService;

    @GetMapping()
    public ResponseEntity<?> myBoard(@AuthenticationPrincipal MyUserDetails myUserDetails) {
        myPageService.나의게시글보기(myUserDetails.getUser().getId());
        return ResponseEntity.ok().body();
    }

}
