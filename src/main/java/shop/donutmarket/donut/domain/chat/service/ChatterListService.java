package shop.donutmarket.donut.domain.chat.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import shop.donutmarket.donut.domain.admin.model.StatusCode;
import shop.donutmarket.donut.domain.board.model.Board;
import shop.donutmarket.donut.domain.board.model.Event;
import shop.donutmarket.donut.domain.board.repository.BoardRepository;
import shop.donutmarket.donut.domain.chat.dto.ChatReq.ChatInviteReqDTO;
import shop.donutmarket.donut.domain.chat.dto.ChatReq.ChatKickReqDTO;
import shop.donutmarket.donut.domain.chat.dto.ChatResp.MyChatListRespDTO;
import shop.donutmarket.donut.domain.chat.model.Chatroom;
import shop.donutmarket.donut.domain.chat.model.ChatterList;
import shop.donutmarket.donut.domain.chat.repository.ChatroomRepository;
import shop.donutmarket.donut.domain.chat.repository.ChatterListRepository;
import shop.donutmarket.donut.domain.participant.model.Participant;
import shop.donutmarket.donut.domain.participant.repository.ParticipantRepository;
import shop.donutmarket.donut.domain.user.model.User;
import shop.donutmarket.donut.domain.user.repository.UserRepository;
import shop.donutmarket.donut.global.auth.MyUserDetails;

@Service
@RequiredArgsConstructor
public class ChatterListService {

    private final ChatroomRepository chatroomRepository;
    private final ChatterListRepository chatterListRepository;
    private final ParticipantRepository participantRepository;
    private final UserRepository userRepository;
    private final BoardRepository boardRepository;

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
