package shop.donutmarket.donut.domain.myPage.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import shop.donutmarket.donut.domain.myPage.dto.MyPageResp;
import shop.donutmarket.donut.domain.myPage.service.MyPageService;
import shop.donutmarket.donut.global.auth.MyUserDetails;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mypage")
public class MyPageController {

    private final MyPageService myPageService;

    @GetMapping("/board")
    public ResponseEntity<?> myBoard(@AuthenticationPrincipal MyUserDetails myUserDetails) {
        List<MyPageResp.MyBoardDTO> myBoardDTOS = myPageService.나의게시글보기(myUserDetails.getUser().getId());
        return ResponseEntity.ok().body(myBoardDTOS);
    }

    @GetMapping("/payment")
    public ResponseEntity<?> myPayment(@AuthenticationPrincipal MyUserDetails myUserDetails) {
        List<MyPageResp.MyPaymentDTO> myPaymentDTOS = myPageService.나의구매내역보기(myUserDetails.getUser().getId());
        return ResponseEntity.ok().body(myPaymentDTOS);
    }
}
