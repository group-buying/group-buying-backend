package shop.donutmarket.donut.domain.participant.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
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

}
