package shop.donutmarket.donut.domain.chat.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import shop.donutmarket.donut.domain.chat.dto.ChatResp.MyChatListRespDTO;
import shop.donutmarket.donut.domain.chat.service.ChatterListService;
import shop.donutmarket.donut.global.auth.MyUserDetails;
import shop.donutmarket.donut.global.dto.ResponseDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/chat")
public class ChatController {
    
    private final ChatterListService chatterListService;

    @GetMapping
    public ResponseEntity<?> roomList(@AuthenticationPrincipal MyUserDetails myUserDetails) {
        MyChatListRespDTO chatList = chatterListService.채팅목록(myUserDetails);
        return new ResponseEntity<>(new ResponseDTO<>().data(chatList), HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> room(@PathVariable Long id, @AuthenticationPrincipal MyUserDetails myUserDetails) {
        // 임시 저장
        return new ResponseEntity<>(new ResponseDTO<>(), HttpStatus.OK);
    }

}