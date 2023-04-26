package shop.donutmarket.donut.domain.chat.controller;


import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import shop.donutmarket.donut.domain.chat.dto.ChatReq.ChatInviteReqDTO;
import shop.donutmarket.donut.domain.chat.dto.ChatReq.ChatKickReqDTO;
import shop.donutmarket.donut.domain.chat.dto.ChatResp.ChatroomListFirebaseRespDTO;
import shop.donutmarket.donut.domain.chat.dto.ChatResp.ChatterListFirebaseRespDTO;
import shop.donutmarket.donut.domain.chat.dto.ChatResp.MyChatListRespDTO;
import shop.donutmarket.donut.domain.chat.service.ChatterListFirebaseService;
import shop.donutmarket.donut.domain.chat.service.ChatterListService;
import shop.donutmarket.donut.global.auth.MyUserDetails;
import shop.donutmarket.donut.global.dto.ResponseDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/chat")
public class ChatController {
    
    private final ChatterListService chatterListService;
    private final ChatterListFirebaseService chatterListFirebaseService;

    @GetMapping
    public ResponseEntity<?> roomList(@AuthenticationPrincipal MyUserDetails myUserDetails) {
        MyChatListRespDTO chatList = chatterListService.채팅목록(myUserDetails);
        return ResponseEntity.ok(chatList);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> room(@PathVariable Long id, @AuthenticationPrincipal MyUserDetails myUserDetails) {
        // 임시 저장===
        return new ResponseEntity<>(new ResponseDTO<>(), HttpStatus.OK);
    }

    @GetMapping("/firebase/chatter")
    public ResponseEntity<?> chatterList() throws Exception {
        List<ChatterListFirebaseRespDTO> list = chatterListFirebaseService.getChatterList();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/firebase/chatroom")
    public ResponseEntity<?> chatroomList() throws Exception {
        List<ChatroomListFirebaseRespDTO> list = chatterListFirebaseService.getChatroomList();
        return ResponseEntity.ok(list);
    }
    
    @PostMapping("/invite")
    public ResponseEntity<?> invite(@RequestBody @Valid ChatInviteReqDTO chatInviteReqDTO, @AuthenticationPrincipal MyUserDetails myUserDetails) {
        chatterListService.초대하기(chatInviteReqDTO);
        return new ResponseEntity<>(new ResponseDTO<>(), HttpStatus.OK);
    }

    @PutMapping("/exit/{id}")
    public ResponseEntity<?> exit(@PathVariable Long id, @AuthenticationPrincipal MyUserDetails myUserDetails) {
        chatterListService.나가기(id, myUserDetails);
        return new ResponseEntity<>(new ResponseDTO<>(), HttpStatus.OK);
    }

    @PutMapping("/kick")
    public ResponseEntity<?> kick(@RequestBody @Valid ChatKickReqDTO chatKickReqDTO, @AuthenticationPrincipal MyUserDetails myUserDetails) {
        chatterListService.강퇴하기(chatKickReqDTO, myUserDetails);
        return new ResponseEntity<>(new ResponseDTO<>(), HttpStatus.OK);
    }
}
