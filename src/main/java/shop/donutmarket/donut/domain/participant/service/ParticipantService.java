package shop.donutmarket.donut.domain.participant.service;

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
import shop.donutmarket.donut.domain.review.model.Rate;
import shop.donutmarket.donut.domain.user.model.User;
import shop.donutmarket.donut.global.auth.MyUserDetails;

@Service
@RequiredArgsConstructor
public class ParticipantService {
    
    private final ParticipantRepository participantRepository;
    private final BoardRepository boardRepository;

    public List<ParticipantListRespDTO> 내참가목록(@AuthenticationPrincipal MyUserDetails myUserDetails) {
        User user = myUserDetails.getUser();
        List<Participant> myParticipantsPS = participantRepository.findAllByUserIdwithEvent(user.getId());
        List<ParticipantListRespDTO> participantList = new ArrayList<>();
        
        for (Participant participant : myParticipantsPS) {
            Event eventPS = participant.getEvent();
            StatusCode statusCodePS = participant.getStatusCode();

            ParticipantListRespDTO participantDTO = ParticipantListRespDTO.builder().event(eventPS).user(user)
            .qty(participant.getQty()).limitTime(participant.getLimitTime()).statusCode(statusCodePS)
            .createdAt(participant.getCreatedAt()).build();

            participantList.add(participantDTO);
        }
        return participantList;
    }

    @Transactional
    public ParticipantSaveRespDTO 참가하기(ParticipantSaveReqDTO participantSaveReqDTO, @AuthenticationPrincipal MyUserDetails myUserDetails) {
        Participant participant = participantSaveReqDTO.toEntity(myUserDetails.getUser());
        Participant particiPS = participantRepository.save(participant);
        Event eventPS = particiPS.getEvent();
        Event event = Event.builder().id(eventPS.getId()).latitude(eventPS.getLatitude()).longtitude(eventPS.getLongtitude())
        .paymentType(eventPS.getPaymentType()).price(eventPS.getPrice()).startAt(eventPS.getStartAt())
        .endAt(eventPS.getEndAt()).createdAt(LocalDateTime.now()).build();



        ParticipantSaveRespDTO saveRespDTO = new ParticipantSaveRespDTO(
            event, myUserDetails.getUser(), participant.getQty(),
            participant.getLimitTime(), participant.getStatusCode(), participant.getCreatedAt());

        return saveRespDTO;
    }

    @Transactional
    public ParticipantSelectRespDTO 채택하기(ParticipantSelectReqDTO participantSelectReqDTO, @AuthenticationPrincipal MyUserDetails myUserDetails) {

        Optional<Participant> particiOP = participantRepository.findById(participantSelectReqDTO.getId());
        if (!particiOP.isPresent()) {
            // 없을때 예외처리
        }
        Participant particiPS = particiOP.get();

        Optional<Board> boardOP = boardRepository.findByEventId(particiPS.getEvent().getId());
        Board boardPS = boardOP.get();
        Long organizerId = boardPS.getOrganizer().getId();

        if(!(organizerId == myUserDetails.getUser().getId())){
            // 권한없을때 처리
        }

        StatusCode seletedCode = StatusCode.builder().id(302).type("participant")
        .status("채택").createdAt(LocalDateTime.now()).build();

        particiPS.selected(seletedCode);

        Event eventPS = particiPS.getEvent();
        Event event = Event.builder().id(eventPS.getId()).latitude(eventPS.getLatitude()).longtitude(eventPS.getLongtitude())
        .qty(eventPS.getQty()).paymentType(eventPS.getPaymentType()).startAt(eventPS.getStartAt()).endAt(eventPS.getEndAt())
        .price(eventPS.getPrice()).createdAt(eventPS.getCreatedAt()).build();

        User userPS = particiPS.getUser();

        Rate userRate = Rate.builder().userId(userPS.getId()).rateName(userPS.getRate().getRateName())
        .ratePoint(userPS.getRate().getRatePoint()).build();

        User user = User.builder().id(userPS.getId()).name(userPS.getName())
        .profile(userPS.getProfile()).rate(userRate).build();

        ParticipantSelectRespDTO selectRespDTO = new ParticipantSelectRespDTO(
            particiPS.getId(), event, user, seletedCode);

        return selectRespDTO;
    }

    @Transactional
    public ParticipantCancleRespDTO 취소하기(ParticipantCancelReqDTO participantCancelReqDTO, @AuthenticationPrincipal MyUserDetails myUserDetails) {

        Optional<Participant> particiOP = participantRepository.findById(participantCancelReqDTO.getId());
        if (!particiOP.isPresent()) {
            // 없을때 예외처리
        }
        Participant particiPS = particiOP.get();
        
        if(!(particiPS.getUser().getId() == myUserDetails.getUser().getId())){
            // 인증 없을때 예외처리
        }

        StatusCode canceled = new StatusCode(303, "participant", "참가 취소", LocalDateTime.now());
        
        particiPS.canceled(canceled);

        Event eventPS = particiPS.getEvent();
        Event event = Event.builder().id(eventPS.getId()).latitude(eventPS.getLatitude()).longtitude(eventPS.getLongtitude())
        .qty(eventPS.getQty()).paymentType(eventPS.getPaymentType()).startAt(eventPS.getStartAt()).endAt(eventPS.getEndAt())
        .price(eventPS.getPrice()).createdAt(eventPS.getCreatedAt()).build();
        
        ParticipantCancleRespDTO cancleRespDTO = new ParticipantCancleRespDTO(
            particiPS.getId(), event, myUserDetails.getUser(), canceled);
            
        return cancleRespDTO;
    }

    @Transactional
    public ParticipantDropRespDTO 강퇴하기(ParticipantDropReqDTO participantDropReqDTO, @AuthenticationPrincipal MyUserDetails myUserDetails) {
        
        Optional<Participant> particiOP = participantRepository.findById(participantDropReqDTO.getId());
        if (!particiOP.isPresent()) {
            // 없을때 예외처리
        }
        Participant particiPS = particiOP.get();
        
        Optional<Board> boardOP = boardRepository.findByEventId(particiPS.getEvent().getId());
        Board boardPS = boardOP.get();
        Long organizerId = boardPS.getOrganizer().getId();

        if(!(organizerId == myUserDetails.getUser().getId())){
            // 권한없을때 처리
        }

        StatusCode droped = new StatusCode(301, "participant", "미채택", LocalDateTime.now());

        Event eventPS = particiPS.getEvent();
        Event event = Event.builder().id(eventPS.getId()).latitude(eventPS.getLatitude()).longtitude(eventPS.getLongtitude())
        .qty(eventPS.getQty()).paymentType(eventPS.getPaymentType()).startAt(eventPS.getStartAt()).endAt(eventPS.getEndAt())
        .price(eventPS.getPrice()).createdAt(eventPS.getCreatedAt()).build();

        User userPS = particiPS.getUser();

        Rate userRate = Rate.builder().userId(userPS.getId()).rateName(userPS.getRate().getRateName())
        .ratePoint(userPS.getRate().getRatePoint()).build();

        User user = User.builder().id(userPS.getId()).name(userPS.getName())
        .profile(userPS.getProfile()).rate(userRate).build();

        ParticipantDropRespDTO dropRespDTO = new ParticipantDropRespDTO(
            particiPS.getId(), event, user, droped);
            
        return dropRespDTO;
    }

}
