package shop.donutmarket.donut.domain.myLocation.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import shop.donutmarket.donut.domain.myLocation.dto.MyLocationReq.MyLocationSaveReqDTO;
import shop.donutmarket.donut.domain.myLocation.dto.MyLocationResp;
import shop.donutmarket.donut.domain.myLocation.dto.MyLocationResp.DefaultMyLocationRespDTO;
import shop.donutmarket.donut.domain.myLocation.dto.MyLocationResp.MyLocationSaveRespDTO;
import shop.donutmarket.donut.domain.myLocation.service.MyLocationService;
import shop.donutmarket.donut.global.auth.MyUserDetails;
import shop.donutmarket.donut.global.dto.ResponseDTO;


@RestController
@RequiredArgsConstructor
@RequestMapping("/myLocations")
public class MyLocationController {
    
    private final MyLocationService myLocationService;

    @PostMapping("/default")
    public ResponseEntity<?> defaultLocation(@AuthenticationPrincipal MyUserDetails myUserDetails){
        DefaultMyLocationRespDTO defaultRespDTO = myLocationService.디폴트지역(myUserDetails);
        ResponseDTO<?> responseDTO = new ResponseDTO<>(defaultRespDTO);
        return ResponseEntity.ok(responseDTO);
    }

    @PutMapping
    public ResponseEntity<?> save(@RequestBody @Valid MyLocationSaveReqDTO myLocationSaveReqDTO, @AuthenticationPrincipal MyUserDetails myUserDetails, BindingResult bindingResult) {
        MyLocationSaveRespDTO saveRespDTO = myLocationService.내지역변경(myLocationSaveReqDTO, myUserDetails);
        ResponseDTO<?> responseDTO = new ResponseDTO<>(saveRespDTO);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping
    public ResponseEntity<?> myLocation(@AuthenticationPrincipal MyUserDetails myUserDetails) {
        MyLocationResp.MyLocationSelectRespDTO myLocationSelectRespDTO = myLocationService.내지역보기(myUserDetails);
        ResponseDTO<?> responseDTO = new ResponseDTO<>(myLocationSelectRespDTO);
        return ResponseEntity.ok(responseDTO);
    }
}
