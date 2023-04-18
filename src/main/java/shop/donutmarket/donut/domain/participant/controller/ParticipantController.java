package shop.donutmarket.donut.domain.participant.controller;

import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import shop.donutmarket.donut.domain.participant.service.ParticipantService;

@RestController
@RequiredArgsConstructor
public class ParticipantController {
    
    private final ParticipantService participantService;
}
