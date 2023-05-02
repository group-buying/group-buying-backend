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
import shop.donutmarket.donut.domain.participant.dto.ParticipantResp;
import shop.donutmarket.donut.domain.participant.dto.ParticipantResp.ParticipantDropRespDTO;
import shop.donutmarket.donut.domain.participant.dto.ParticipantResp.ParticipantListRespDTO;
import shop.donutmarket.donut.domain.participant.dto.ParticipantResp.ParticipantSaveRespDTO;
import shop.donutmarket.donut.domain.participant.dto.ParticipantResp.ParticipantSelectRespDTO;
import shop.donutmarket.donut.domain.participant.model.Participant;
import shop.donutmarket.donut.domain.participant.repository.ParticipantRepository;
import shop.donutmarket.donut.domain.user.model.User;
import shop.donutmarket.donut.domain.user.repository.UserRepository;
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
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public ParticipantListRespDTO 내참가목록(@AuthenticationPrincipal MyUserDetails myUserDetails) {
        Optional<User> userOP = userRepository.findByIdJoinFetch(myUserDetails.getUser().getId());
        if (userOP.isEmpty()) {
            throw new Exception404("존재하지 않는 유저입니다");
        }

        try {
            User userPS = userOP.get();
            List<Participant> myParticipantsPS = participantRepository.findAllByUserIdwithEvent(userPS.getId());
            ParticipantResp.ParticipantListRespDTO participantList = new ParticipantListRespDTO(myParticipantsPS);
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
        Optional<Participant> participantOP = participantRepository.findByIdJoinFetch(participantSelectReqDTO.getParticipantId());
        if (participantOP.isEmpty()) {
            throw new Exception404("존재하지 않는 참가자입니다");
        }

        Participant participantPS = participantOP.get();

        Optional<Board> boardOP = boardRepository.findByEventId(participantSelectReqDTO.getEventId());
        Board boardPS = boardOP.get();
        Long organizerId = boardPS.getOrganizer().getId();

        if (!(Objects.equals(organizerId, myUserDetails.getUser().getId()))) {
            throw new Exception403("참가자를 채택할 권한이 없습니다");
        }

        try {
            participantPS.updateStatusCode(301); // 채택 시

            // 프록시 객체를 초기화하기 위해 한 번 더 조회
            Optional<Participant> participantOP2 = participantRepository.findByIdJoinFetch(participantSelectReqDTO.getParticipantId());
            Participant participantPS2 = participantOP2.get();
            ParticipantSelectRespDTO selectRespDTO = new ParticipantSelectRespDTO(
                    participantPS2);

            return selectRespDTO;
        } catch (Exception e) {
            throw new Exception500("참가자 채택하기 실패 : " + e.getMessage());
        }
    }

    @Transactional
    public ParticipantResp.ParticipantCancelRespDTO 취소하기(ParticipantCancelReqDTO participantCancelReqDTO, @AuthenticationPrincipal MyUserDetails myUserDetails) {
        Optional<Participant> participantOP = participantRepository.findByIdJoinFetch(participantCancelReqDTO.getParticipantId());
        if (participantOP.isEmpty()) {
            throw new Exception404("존재하지 않는 참가자입니다");
        }
        Participant participantPS = participantOP.get();

        Optional<Board> boardOP = boardRepository.findByEventId(participantCancelReqDTO.getEventId());
        Board boardPS = boardOP.get();
        Long organizerId = boardPS.getOrganizer().getId();

        if (!(Objects.equals(organizerId, myUserDetails.getUser().getId()))) {
            throw new Exception403("참가자를 취소할 권한이 없습니다");
        }

        try {
            participantPS.updateStatusCode(302); // 채택 안됐을 시

            // 프록시 객체를 초기화하기 위해 한 번 더 조회
            Optional<Participant> participantOP2 = participantRepository.findByIdJoinFetch(participantCancelReqDTO.getParticipantId());
            Participant participantPS2 = participantOP2.get();
            ParticipantResp.ParticipantCancelRespDTO cancleRespDTO = new ParticipantResp.ParticipantCancelRespDTO(
                    participantPS2);

            return cancleRespDTO;
        } catch (Exception e) {
            throw new Exception500("참가자 채택 취소하기 실패 : " + e.getMessage());
        }
    }

    @Transactional
    public ParticipantDropRespDTO 강퇴하기(ParticipantDropReqDTO participantDropReqDTO, @AuthenticationPrincipal MyUserDetails myUserDetails) {
        Optional<Participant> participantOP = participantRepository.findByIdJoinFetch(participantDropReqDTO.getParticipantId());
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
            participantPS.updateStatusCode(303); // 강퇴당한 회원일 시

            // 프록시 객체를 초기화하기 위해 한 번 더 조회
            Optional<Participant> participantOP2 = participantRepository.findByIdJoinFetch(participantDropReqDTO.getParticipantId());
            Participant participantPS2 = participantOP2.get();

            ParticipantDropRespDTO dropRespDTO = new ParticipantDropRespDTO(
                    participantPS2);

            return dropRespDTO;
        } catch (Exception e) {
            throw new Exception500("참가자 강퇴하기 실패 : " + e.getMessage());
        }
    }
}
