package shop.donutmarket.donut.domain.participant.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import shop.donutmarket.donut.domain.admin.model.StatusCode;
import shop.donutmarket.donut.domain.participant.dto.ParticipantReq.ParticipantSaveReqDTO;
import shop.donutmarket.donut.domain.participant.dto.ParticipantReq.ParticipantSelectReqDTO;
import shop.donutmarket.donut.domain.participant.dto.ParticipantResp.ParticipantCancleRespDTO;
import shop.donutmarket.donut.domain.participant.dto.ParticipantResp.ParticipantDropRespDTO;
import shop.donutmarket.donut.domain.participant.dto.ParticipantResp.ParticipantSaveRespDTO;
import shop.donutmarket.donut.domain.participant.dto.ParticipantResp.ParticipantSelectRespDTO;
import shop.donutmarket.donut.domain.participant.model.Participant;
import shop.donutmarket.donut.domain.participant.repository.ParticipantRepository;

@Service
@RequiredArgsConstructor
public class ParticipantService {
    
    private final ParticipantRepository participantRepository;

    public List<Participant> 내참가목록(Long id) {
        List<Participant> myParticipants = participantRepository.findAllByUserId(id);
        return myParticipants;
    }

    @Transactional
    public ParticipantSaveRespDTO 참가하기(ParticipantSaveReqDTO participantSaveReqDTO) {
        Participant participant = participantSaveReqDTO.toEntity();
        Participant particiPS = participantRepository.save(participant);

        ParticipantSaveRespDTO saveRespDTO = new ParticipantSaveRespDTO(
            particiPS.getId(), particiPS.getEvent(), particiPS.getUser(), particiPS.getQty(),
            particiPS.getLimitTime(), particiPS.getStatusCode(), particiPS.getCreatedAt());

        return saveRespDTO;
    }

    @Transactional
    public ParticipantSelectRespDTO 채택하기(ParticipantSelectReqDTO participantSelectReqDTO) {
        Participant participant = participantSelectReqDTO.toEntity();

        Participant particiPS = participantRepository.save(participant);

        ParticipantSelectRespDTO selectRespDTO = new ParticipantSelectRespDTO(
            particiPS.getId(), particiPS.getEvent(), particiPS.getUser(), particiPS.getStatusCode());
        return selectRespDTO;
    }

    @Transactional
    public ParticipantCancleRespDTO 취소하기(Long id) {
        StatusCode cancled = new StatusCode(303, "participant", "참가 취소", LocalDateTime.now());
        
        Participant participant = Participant.builder().id(id).statusCode(cancled).build();
        
        Participant particiPS = participantRepository.save(participant);
        
        ParticipantCancleRespDTO cancleRespDTO = new ParticipantCancleRespDTO(
        particiPS.getId(), particiPS.getEvent(), particiPS.getUser(), particiPS.getStatusCode());
            
        return cancleRespDTO;
    }

    @Transactional
    public ParticipantDropRespDTO 강퇴하기(Long id) {
        StatusCode droped = new StatusCode(301, "participant", "미채택", LocalDateTime.now());
            
        Participant participant = Participant.builder().id(id).statusCode(droped).build();
        Participant particiPS = participantRepository.save(participant);

        ParticipantDropRespDTO dropRespDTO = new ParticipantDropRespDTO(
        particiPS.getId(), particiPS.getEvent(), particiPS.getUser(), particiPS.getStatusCode());
            
        return dropRespDTO;
    }

}
