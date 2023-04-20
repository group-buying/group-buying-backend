package shop.donutmarket.donut.domain.participant.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import shop.donutmarket.donut.domain.participant.dto.ParticipantReq.ParticipantSaveReqDTO;
import shop.donutmarket.donut.domain.participant.dto.ParticipantReq.ParticipantSelectReqDTO;
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

}
