package shop.donutmarket.donut.domain.report.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import shop.donutmarket.donut.domain.report.dto.ReportReq;
import shop.donutmarket.donut.domain.report.dto.ReportResp;
import shop.donutmarket.donut.domain.report.service.ReportService;
import shop.donutmarket.donut.global.auth.MyUserDetails;
import shop.donutmarket.donut.global.dto.ResponseDTO;

@RestController
@RequiredArgsConstructor
public class ReportController {
    
    private final ReportService reportService;

    @PostMapping("/reports/{id}") //신고할 사람의 id를 받아온다
    public ResponseEntity<?> insert(@AuthenticationPrincipal MyUserDetails myUserDetails,
                                    @PathVariable Long id,
                                    @RequestBody @Valid ReportReq.insertDTO insertDTO,
                                    BindingResult bindingResult) {
        ReportResp.InsertRespDTO respDTO = reportService.신고하기(myUserDetails, id, insertDTO);
        ResponseDTO<?> responseDTO = new ResponseDTO<>(respDTO);
        return ResponseEntity.ok(responseDTO);
    }
}
