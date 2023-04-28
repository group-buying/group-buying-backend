package shop.donutmarket.donut.domain.chat.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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
import shop.donutmarket.donut.global.exception.Exception403;
import shop.donutmarket.donut.global.exception.Exception404;
import shop.donutmarket.donut.global.exception.Exception500;

@Service
@RequiredArgsConstructor
public class ChatterListService {

    private final ChatroomRepository chatroomRepository;
    private final ChatterListRepository chatterListRepository;
    private final ParticipantRepository participantRepository;
    private final UserRepository userRepository;
    private final BoardRepository boardRepository;

    @Transactional(readOnly = true)
    public MyChatListRespDTO 채팅목록(@AuthenticationPrincipal MyUserDetails myUserDetails) {
        User user = myUserDetails.getUser();
        try {
            List<ChatterList> listPS = chatterListRepository.findAllByUserId(user);
            List<ChatterList> listDTO = new ArrayList<>();
            for (ChatterList chatterList : listPS) {
                ChatterList list = ChatterList.builder().id(chatterList.getId()).userId(myUserDetails.getUser())
                        .chatroomId(chatterList.getChatroomId()).createdAt(chatterList.getCreatedAt()).build();
                listDTO.add(list);
            }
            MyChatListRespDTO listRespDTO = new MyChatListRespDTO(listDTO);

            return listRespDTO;
        } catch (Exception e) {
            throw new Exception500("채팅 목록 보기 실패 : " + e.getMessage());
        }
    }

    @Transactional
    public void 채팅방(@AuthenticationPrincipal MyUserDetails myUserDetails) {
        User user = myUserDetails.getUser();
    }

    @Transactional
    public void 초대하기(ChatInviteReqDTO chatInviteReqDTO) {
        Optional<Participant> participantOP = participantRepository.findById(chatInviteReqDTO.getParticipantId());
        if (participantOP.isEmpty()) {
            throw new Exception404("존재하지 않는 참가자입니다");
        }
        Optional<User> userOP = userRepository.findById(chatInviteReqDTO.getInvitedUserId());
        if (userOP.isEmpty()) {
            throw new Exception404("존재하지 않는 회원입니다");
        }
        User userPS = userOP.get();

        Optional<Chatroom> chatroomOP = chatroomRepository.findByIdwithEvent(chatInviteReqDTO.getChatroomId());
        if (chatroomOP.isEmpty()) {
            throw new Exception404("존재하지 않는 채팅방입니다");
        }
        Chatroom chatroomPS = chatroomOP.get();

        try {
            Participant participantPS = participantOP.get();

            // 참가자 채택됨 변환
            participantPS.selected();

            // 채팅 참가 목록 생성
            ChatterList newchatter = ChatterList.builder().userId(userPS).chatroomId(chatroomPS).createdAt(LocalDateTime.now()).build();
            newchatter.participate();
            chatterListRepository.save(newchatter);
        } catch (Exception e) {
            throw new Exception500("채팅 초대하기 실패 : " + e.getMessage());
        }
    }

    @Transactional
    public void 나가기(Long id, @AuthenticationPrincipal MyUserDetails myUserDetails) {
        Optional<ChatterList> chatterOP = chatterListRepository.findById(id);
        if (chatterOP.isEmpty()) {
            throw new Exception404("존재하지 않는 채팅방입니다");
        }
        ChatterList chatterPS = chatterOP.get();
        if (!(Objects.equals(chatterPS.getUserId().getId(), myUserDetails.getUser().getId()))) {
            throw new Exception403("본인의 채팅방만 나갈 수 있습니다");
        }

        try {
            chatterPS.exit();
        } catch (Exception e) {
            throw new Exception500("채팅방 나가기 실패 : " + e.getMessage());
        }
    }

    @Transactional
    public void 강퇴하기(ChatKickReqDTO chatKickReqDTO, @AuthenticationPrincipal MyUserDetails myUserDetails) {
        Optional<Board> boardOP = boardRepository.findByEventId(chatKickReqDTO.getEventId());
        if (boardOP.isEmpty()) {
            throw new Exception404("존재하지 않는 게시글입니다");
        }

        Board boardPS = boardOP.get();
        if (!(Objects.equals(boardPS.getOrganizer().getId(), myUserDetails.getUser().getId()))) {
            throw new Exception403("주최자만 강퇴할 수 있습니다");
        }

        Optional<ChatterList> chatterOP = chatterListRepository.findById(chatKickReqDTO.getChatterListId());
        if (chatterOP.isEmpty()) {
            throw new Exception404("존재하지 않는 채팅방입니다");
        }

        try {
            ChatterList chatterPS = chatterOP.get();
            chatterPS.kick();
        } catch (Exception e) {
            throw new Exception500("채팅방 강퇴하기 실패 : " + e.getMessage());
        }
    }
}
