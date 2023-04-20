package shop.donutmarket.donut.domain.participant.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import shop.donutmarket.donut.domain.participant.dto.ParticipantReq.ParticipantSaveReqDTO;
import shop.donutmarket.donut.domain.participant.dto.ParticipantReq.ParticipantSelectReqDTO;
import shop.donutmarket.donut.domain.participant.dto.ParticipantResp.ParticipantCancleRespDTO;
import shop.donutmarket.donut.domain.participant.dto.ParticipantResp.ParticipantDropRespDTO;
import shop.donutmarket.donut.domain.participant.dto.ParticipantResp.ParticipantSaveRespDTO;
import shop.donutmarket.donut.domain.participant.dto.ParticipantResp.ParticipantSelectRespDTO;
import shop.donutmarket.donut.domain.participant.model.Participant;
import shop.donutmarket.donut.domain.participant.service.ParticipantService;
import shop.donutmarket.donut.global.dto.ResponseDTO;

@RestController
@RequiredArgsConstructor
public class ParticipantController {
    
    private final ParticipantService participantService;

    @GetMapping("/participants")
    public @ResponseBody ResponseEntity<?> myParticipants(@RequestBody Long id) {
        List<Participant> participantList = participantService.내참가목록(id);

        return new ResponseEntity<>(new ResponseDTO<>().data(participantList), HttpStatus.OK);
    }

    @PostMapping("/participants")
    public @ResponseBody ResponseEntity<?> save(@RequestBody @Vaild ParticipantSaveReqDTO participantSaveReqDTO) {
        ParticipantSaveRespDTO saveRespDTO = participantService.참가하기(participantSaveReqDTO);

        return new ResponseEntity<>(new ResponseDTO<>().data(saveRespDTO), HttpStatus.CREATED);
    }

    @PutMapping("/participants")
    public @ResponseBody ResponseEntity<?> select(@RequestBody @Vaild ParticipantSelectReqDTO participantSelectReqDTO) {
        ParticipantSelectRespDTO selectRespDTO = participantService.채택하기(participantSelectReqDTO);

        return new ResponseEntity<>(new ResponseDTO<>().data(selectRespDTO), HttpStatus.CREATED);
    }

    @DeleteMapping("/participants")
    public @ResponseBody ResponseEntity<?> cancle(@RequestBody Long id) {
        ParticipantCancleRespDTO cancleRespDTO = participantService.취소하기(id);
        
        return new ResponseEntity<>(new ResponseDTO<>().data(cancleRespDTO), HttpStatus.CREATED);
    }

    @DeleteMapping("/participants")
    public @ResponseBody ResponseEntity<?> drop(@RequestBody Long id) {
        ParticipantDropRespDTO dropRespDTO = participantService.강퇴하기(id);

        return new ResponseEntity<>(new ResponseDTO<>().data(dropRespDTO), HttpStatus.CREATED);
    }

}
