package shop.donutmarket.donut.domain.chat.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import shop.donutmarket.donut.domain.chat.dto.ChatRespDto.MyChatListRespDTO;
import shop.donutmarket.donut.domain.chat.model.ChatterList;
import shop.donutmarket.donut.domain.chat.repository.ChatroomRepository;
import shop.donutmarket.donut.domain.chat.repository.ChatterListRepository;
import shop.donutmarket.donut.domain.user.model.User;
import shop.donutmarket.donut.global.auth.MyUserDetails;

@Service
@RequiredArgsConstructor
public class ChatterListService {

    private final ChatroomRepository chatroomRepository;
    private final ChatterListRepository chatterListRepository;

    public MyChatListRespDTO 채팅목록(@AuthenticationPrincipal MyUserDetails myUserDetails) {
        User user = myUserDetails.getUser();
        List<ChatterList> listPS = chatterListRepository.findAllByUserId(user);
        List<ChatterList> listDTO = new ArrayList<>();
        for (ChatterList chatterList : listPS) {
            ChatterList list = ChatterList.builder().id(chatterList.getId()).userId(myUserDetails.getUser())
            .chatroomId(chatterList.getChatroomId()).createdAt(chatterList.getCreatedAt()).build();
            listDTO.add(list);
        }
        MyChatListRespDTO listRespDTO = new MyChatListRespDTO(listDTO);

        return listRespDTO;
    }
    
}
