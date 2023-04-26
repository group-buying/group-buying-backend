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

    @Transactional
    public void 초대하기(ChatInviteReqDTO chatInviteReqDTO) {
        Optional<Participant> particiOP = participantRepository.findById(chatInviteReqDTO.getParticipantId());
        Participant particiPS = particiOP.get();
        // 참가자 채택됨 변환
        particiPS.selected();
        
        Optional<User> userOP = userRepository.findById(chatInviteReqDTO.getInvitedUserId());
        User userPS = userOP.get();
        Optional<Chatroom> chatroomOP = chatroomRepository.findByIdwithEvent(chatInviteReqDTO.getChatroomId());
        Chatroom chatroomPS = chatroomOP.get();
        
        // 채팅 참가 목록 생성
        ChatterList newchatter = ChatterList.builder().userId(userPS).chatroomId(chatroomPS).createdAt(LocalDateTime.now()).build();
        newchatter.partici();
        chatterListRepository.save(newchatter);
    }
    
    public void 나가기(Long id, @AuthenticationPrincipal MyUserDetails myUserDetails) {
        Optional<ChatterList> chatterOP = chatterListRepository.findById(id);
        ChatterList chatterPS = chatterOP.get();
        if(!(chatterPS.getUserId().getId() == myUserDetails.getUser().getId())){
            // 권한 없음
        }

        chatterPS.exit();
    }

    public void 강퇴하기(ChatKickReqDTO chatKickReqDTO, @AuthenticationPrincipal MyUserDetails myUserDetails) {
        Optional<Board> boardOP = boardRepository.findByEventId(chatKickReqDTO.getEventId());
        Board boardPS = boardOP.get();
        if(!(boardPS.getOrganizer().getId() == myUserDetails.getUser().getId())){
            // 권한 없음
        }
        
        Optional<ChatterList> chatterOP = chatterListRepository.findById(chatKickReqDTO.getChatterListId());
        ChatterList chatterPS = chatterOP.get();
        StatusCode kickedCode = StatusCode.builder().id(702).type("chatter").status("강퇴당함").build();
        chatterPS.kick(kickedCode);
    }
}
