package shop.donutmarket.donut.domain.participant.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import shop.donutmarket.donut.domain.participant.dto.ParticipantReq.ParticipantCancelReqDTO;
import shop.donutmarket.donut.domain.participant.dto.ParticipantReq.ParticipantDropReqDTO;
import shop.donutmarket.donut.domain.participant.dto.ParticipantReq.ParticipantSaveReqDTO;
import shop.donutmarket.donut.domain.participant.dto.ParticipantReq.ParticipantSelectReqDTO;
import shop.donutmarket.donut.domain.participant.dto.ParticipantResp;
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
        ParticipantListRespDTO participantList = participantService.내참가목록(myUserDetails);
        ResponseDTO<?> responseDTO = new ResponseDTO<>(participantList);
        return ResponseEntity.ok(responseDTO);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody @Valid ParticipantSaveReqDTO participantSaveReqDTO, BindingResult bindingResult, @AuthenticationPrincipal MyUserDetails myUserDetails) {
        ParticipantSaveRespDTO saveRespDTO = participantService.참가하기(participantSaveReqDTO, myUserDetails);
        ResponseDTO<?> responseDTO = new ResponseDTO<>(saveRespDTO);
        return ResponseEntity.ok(responseDTO);
    }

    @PutMapping("/select")
    public ResponseEntity<?> select(@RequestBody @Valid ParticipantSelectReqDTO participantSelectReqDTO, BindingResult bindingResult, @AuthenticationPrincipal MyUserDetails myUserDetails) {
        ParticipantSelectRespDTO selectRespDTO = participantService.채택하기(participantSelectReqDTO, myUserDetails);
        ResponseDTO<?> responseDTO = new ResponseDTO<>(selectRespDTO);
        return ResponseEntity.ok(responseDTO);
    }

    @PutMapping("/cancel")
    public ResponseEntity<?> cancel(@RequestBody ParticipantCancelReqDTO participantCancelReqDTO, BindingResult bindingResult, @AuthenticationPrincipal MyUserDetails myUserDetails) {
        ParticipantResp.ParticipantCancelRespDTO cancelRespDTO = participantService.취소하기(participantCancelReqDTO, myUserDetails);
        ResponseDTO<?> responseDTO = new ResponseDTO<>(cancelRespDTO);
        return ResponseEntity.ok(responseDTO);
    }

    @PutMapping("/drop")
    public ResponseEntity<?> drop(@RequestBody ParticipantDropReqDTO participantDropReqDTO, BindingResult bindingResult, @AuthenticationPrincipal MyUserDetails myUserDetails) {
        ParticipantDropRespDTO dropRespDTO = participantService.강퇴하기(participantDropReqDTO, myUserDetails);
        ResponseDTO<?> responseDTO = new ResponseDTO<>(dropRespDTO);
        return ResponseEntity.ok(responseDTO);
    }
}
