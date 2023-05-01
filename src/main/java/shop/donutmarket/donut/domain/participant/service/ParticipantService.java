package shop.donutmarket.donut.domain.participant.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import shop.donutmarket.donut.domain.board.model.Board;
import shop.donutmarket.donut.domain.board.model.Event;
import shop.donutmarket.donut.domain.board.repository.BoardRepository;
import shop.donutmarket.donut.domain.board.repository.EventRepository;
import shop.donutmarket.donut.domain.participant.dto.ParticipantReq.ParticipantCancelReqDTO;
import shop.donutmarket.donut.domain.participant.dto.ParticipantReq.ParticipantDropReqDTO;
import shop.donutmarket.donut.domain.participant.dto.ParticipantReq.ParticipantSaveReqDTO;
import shop.donutmarket.donut.domain.participant.dto.ParticipantReq.ParticipantSelectReqDTO;
import shop.donutmarket.donut.domain.participant.dto.ParticipantResp.ParticipantCancleRespDTO;
import shop.donutmarket.donut.domain.participant.dto.ParticipantResp.ParticipantDropRespDTO;
import shop.donutmarket.donut.domain.participant.dto.ParticipantResp.ParticipantListRespDTO;
import shop.donutmarket.donut.domain.participant.dto.ParticipantResp.ParticipantSaveRespDTO;
import shop.donutmarket.donut.domain.participant.dto.ParticipantResp.ParticipantSelectRespDTO;
import shop.donutmarket.donut.domain.participant.model.Participant;
import shop.donutmarket.donut.domain.participant.repository.ParticipantRepository;
import shop.donutmarket.donut.domain.user.model.User;
import shop.donutmarket.donut.global.auth.MyUserDetails;
import shop.donutmarket.donut.global.exception.Exception400;
import shop.donutmarket.donut.global.exception.Exception403;
import shop.donutmarket.donut.global.exception.Exception404;
import shop.donutmarket.donut.global.exception.Exception500;

@Service
@RequiredArgsConstructor
public class ParticipantService {

    private final ParticipantRepository participantRepository;
    private final BoardRepository boardRepository;
    private final EventRepository eventRepository;

    @Transactional(readOnly = true)
    public List<ParticipantListRespDTO> 내참가목록(@AuthenticationPrincipal MyUserDetails myUserDetails) {
        try {
            User user = myUserDetails.getUser();
            List<Participant> myParticipantsPS = participantRepository.findAllByUserIdwithEvent(user.getId());
            List<ParticipantListRespDTO> participantList = new ArrayList<>();

            for (Participant participant : myParticipantsPS) {
                Event eventPS = participant.getEvent();

                ParticipantListRespDTO participantDTO = ParticipantListRespDTO.builder().event(eventPS).user(user)
                        .qty(participant.getQty()).limitTime(participant.getLimitTime())
                        .createdAt(participant.getCreatedAt()).build();

                participantList.add(participantDTO);
            }
            return participantList;
        } catch (Exception e) {
            throw new Exception500("내 참가목록 보기 실패 : " + e.getMessage());
        }
    }

    @Transactional
    public ParticipantSaveRespDTO 참가하기(ParticipantSaveReqDTO participantSaveReqDTO, @AuthenticationPrincipal MyUserDetails myUserDetails) {
        Optional<Participant> participantOP = participantRepository.findByUserIdAndEvendId(myUserDetails.getUser().getId(), participantSaveReqDTO.getEventId());

        if (participantOP.isPresent()) {
            throw new Exception400("이미 참가한 이벤트입니다");
        }

        Optional<Event> eventOP = eventRepository.findById(participantSaveReqDTO.getEventId());

        if (eventOP.isEmpty()) {
            throw new Exception404("존재하지 않는 이벤트입니다");
        }

        try {
            Event eventPS = eventOP.get();
            Participant participant = participantSaveReqDTO.toEntity(myUserDetails.getUser(), eventPS);
            participantRepository.save(participant);

            ParticipantSaveRespDTO saveRespDTO = new ParticipantSaveRespDTO(participant);

            return saveRespDTO;
        } catch (Exception e) {
            throw new Exception500("이벤트 참가하기 실패 : " + e.getMessage());
        }
    }

    @Transactional
    public ParticipantSelectRespDTO 채택하기(ParticipantSelectReqDTO participantSelectReqDTO, @AuthenticationPrincipal MyUserDetails myUserDetails) {
        Optional<Participant> participantOP = participantRepository.findByIdwithEvent(participantSelectReqDTO.getId());
        if (participantOP.isEmpty()) {
            throw new Exception404("존재하지 않는 참가자입니다");
        }

        Participant participantPS = participantOP.get();

        Optional<Board> boardOP = boardRepository.findByEventId(participantPS.getEvent().getId());
        Board boardPS = boardOP.get();
        Long organizerId = boardPS.getOrganizer().getId();

        if (!(Objects.equals(organizerId, myUserDetails.getUser().getId()))) {
            throw new Exception403("참가자를 채택할 권한이 없습니다");
        }

        try {
            Event eventPS = participantPS.getEvent();
            User userPS = participantPS.getUser();

            User user = User.builder().id(userPS.getId()).name(userPS.getName())
                    .profile(userPS.getProfile()).rate(userPS.getRate()).build();

            ParticipantSelectRespDTO selectRespDTO = new ParticipantSelectRespDTO(
                    participantPS.getId(), eventPS, user, "채택함");

            return selectRespDTO;
        } catch (Exception e) {
            throw new Exception500("참가자 채택하기 실패 : " + e.getMessage());
        }
    }

    @Transactional
    public ParticipantCancleRespDTO 취소하기(ParticipantCancelReqDTO participantCancelReqDTO, @AuthenticationPrincipal MyUserDetails myUserDetails) {
        Optional<Participant> participantOP = participantRepository.findByIdwithEvent(participantCancelReqDTO.getId());
        if (participantOP.isEmpty()) {
            throw new Exception404("존재하지 않는 참가자입니다");
        }
        Participant participantPS = participantOP.get();

        if (!(Objects.equals(participantPS.getUser().getId(), myUserDetails.getUser().getId()))) {
            throw new Exception403("참가자를 취소할 권한이 없습니다");
        }

        try {
            Event eventPS = participantPS.getEvent();

            ParticipantCancleRespDTO cancleRespDTO = new ParticipantCancleRespDTO(
                    participantPS.getId(), eventPS, myUserDetails.getUser(), "취소함");

            return cancleRespDTO;
        } catch (Exception e) {
            throw new Exception500("참가자 채택 취소하기 실패 : " + e.getMessage());
        }
    }

    @Transactional
    public ParticipantDropRespDTO 강퇴하기(ParticipantDropReqDTO participantDropReqDTO, @AuthenticationPrincipal MyUserDetails myUserDetails) {
        Optional<Participant> participantOP = participantRepository.findByIdwithEvent(participantDropReqDTO.getId());
        if (participantOP.isEmpty()) {
            throw new Exception404("존재하지 않는 참가자입니다");
        }
        Participant participantPS = participantOP.get();

        Optional<Board> boardOP = boardRepository.findByEventId(participantPS.getEvent().getId());
        Board boardPS = boardOP.get();
        Long organizerId = boardPS.getOrganizer().getId();

        if (!(Objects.equals(organizerId, myUserDetails.getUser().getId()))) {
            throw new Exception403("참가자를 강퇴할 권한이 없습니다");
        }
        try {
            Event eventPS = participantPS.getEvent();
            User userPS = participantPS.getUser();

            User user = User.builder().id(userPS.getId()).name(userPS.getName())
                    .profile(userPS.getProfile()).rate(userPS.getRate()).build();

            ParticipantDropRespDTO dropRespDTO = new ParticipantDropRespDTO(
                    participantPS.getId(), eventPS, user, "강퇴함");

            return dropRespDTO;
        } catch (Exception e) {
            throw new Exception500("참가자 강퇴하기 실패 : " + e.getMessage());
        }
    }
}
