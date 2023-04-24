package shop.donutmarket.donut.domain.participant.controller;

import java.util.List;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import shop.donutmarket.donut.domain.participant.dto.ParticipantReq.ParticipantCancelReqDTO;
import shop.donutmarket.donut.domain.participant.dto.ParticipantReq.ParticipantDropReqDTO;
import shop.donutmarket.donut.domain.participant.dto.ParticipantReq.ParticipantSaveReqDTO;
import shop.donutmarket.donut.domain.participant.dto.ParticipantReq.ParticipantSelectReqDTO;
import shop.donutmarket.donut.domain.participant.dto.ParticipantResp.ParticipantCancleRespDTO;
import shop.donutmarket.donut.domain.participant.dto.ParticipantResp.ParticipantDropRespDTO;
import shop.donutmarket.donut.domain.participant.dto.ParticipantResp.ParticipantListRespDTO;
import shop.donutmarket.donut.domain.participant.dto.ParticipantResp.ParticipantSaveRespDTO;
import shop.donutmarket.donut.domain.participant.dto.ParticipantResp.ParticipantSelectRespDTO;
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

        List<ParticipantListRespDTO> participantList = participantService.내참가목록(myUserDetails);

        return new ResponseEntity<>(new ResponseDTO<>().data(participantList), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<?> save(@RequestBody @Valid ParticipantSaveReqDTO participantSaveReqDTO, @AuthenticationPrincipal MyUserDetails myUserDetails) {
        ParticipantSaveRespDTO saveRespDTO = participantService.참가하기(participantSaveReqDTO, myUserDetails);
        return new ResponseEntity<>(new ResponseDTO<>().data(saveRespDTO), HttpStatus.CREATED);
    }

    @PutMapping("/select")
    public ResponseEntity<?> select(@RequestBody @Valid ParticipantSelectReqDTO participantSelectReqDTO, @AuthenticationPrincipal MyUserDetails myUserDetails) {
        ParticipantSelectRespDTO selectRespDTO = participantService.채택하기(participantSelectReqDTO, myUserDetails);
        return new ResponseEntity<>(new ResponseDTO<>().data(selectRespDTO), HttpStatus.CREATED);
    }

    @PutMapping("/cancel")
    public ResponseEntity<?> cancel(@RequestBody ParticipantCancelReqDTO participantCancelReqDTO, @AuthenticationPrincipal MyUserDetails myUserDetails) {
        ParticipantCancleRespDTO cancleRespDTO = participantService.취소하기(participantCancelReqDTO, myUserDetails);
        return new ResponseEntity<>(new ResponseDTO<>().data(cancleRespDTO), HttpStatus.CREATED);
    }

    @PutMapping("/drop")
    public ResponseEntity<?> drop(@RequestBody ParticipantDropReqDTO participantDropReqDTO, @AuthenticationPrincipal MyUserDetails myUserDetails) {
        ParticipantDropRespDTO dropRespDTO = participantService.강퇴하기(participantDropReqDTO, myUserDetails);
        return new ResponseEntity<>(new ResponseDTO<>().data(dropRespDTO), HttpStatus.CREATED);
    }

}
