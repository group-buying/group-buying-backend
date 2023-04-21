package shop.donutmarket.donut.domain.participant.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import shop.donutmarket.donut.domain.participant.dto.ParticipantReq.ParticipantSaveReqDTO;
import shop.donutmarket.donut.domain.participant.dto.ParticipantReq.ParticipantSelectReqDTO;
import shop.donutmarket.donut.domain.participant.dto.ParticipantResp.ParticipantCancleRespDTO;
import shop.donutmarket.donut.domain.participant.dto.ParticipantResp.ParticipantDropRespDTO;
import shop.donutmarket.donut.domain.participant.dto.ParticipantResp.ParticipantSaveRespDTO;
import shop.donutmarket.donut.domain.participant.dto.ParticipantResp.ParticipantSelectRespDTO;
import shop.donutmarket.donut.domain.participant.model.Participant;
import shop.donutmarket.donut.domain.participant.service.ParticipantService;
import shop.donutmarket.donut.global.auth.MyUserDetails;
import shop.donutmarket.donut.global.dto.ResponseDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/participants")
public class ParticipantController {
    
    private final ParticipantService participantService;

    @GetMapping
    public ResponseEntity<?> myParticipants(@AuthenticationPrincipal MyUserDetails myUserDetails) {

        List<Participant> participantList = participantService.내참가목록(myUserDetails);

        return new ResponseEntity<>(new ResponseDTO<>().data(participantList), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody @Valid ParticipantSaveReqDTO participantSaveReqDTO, @AuthenticationPrincipal MyUserDetails myUserDetails) {
        ParticipantSaveRespDTO saveRespDTO = participantService.참가하기(participantSaveReqDTO, myUserDetails);

        return new ResponseEntity<>(new ResponseDTO<>().data(saveRespDTO), HttpStatus.CREATED);
    }

    @PutMapping("/select")
    public ResponseEntity<?> select(@RequestBody @Valid ParticipantSelectReqDTO participantSelectReqDTO) {
        ParticipantSelectRespDTO selectRespDTO = participantService.채택하기(participantSelectReqDTO);

        return new ResponseEntity<>(new ResponseDTO<>().data(selectRespDTO), HttpStatus.CREATED);
    }

    @PutMapping("/cancel")
    public ResponseEntity<?> cancel(@RequestBody Long id) {
        ParticipantCancleRespDTO cancleRespDTO = participantService.취소하기(id);
        
        return new ResponseEntity<>(new ResponseDTO<>().data(cancleRespDTO), HttpStatus.CREATED);
    }

    @PutMapping("/drop")
    public ResponseEntity<?> drop(@RequestBody Long id) {
        ParticipantDropRespDTO dropRespDTO = participantService.강퇴하기(id);

        return new ResponseEntity<>(new ResponseDTO<>().data(dropRespDTO), HttpStatus.CREATED);
    }

}
