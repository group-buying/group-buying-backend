package shop.donutmarket.donut.domain.participant.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import shop.donutmarket.donut.domain.participant.repository.ParticipantRepository;

@Service
@RequiredArgsConstructor
public class ParticipantService {
    
    private final ParticipantRepository participantRepository;
}
