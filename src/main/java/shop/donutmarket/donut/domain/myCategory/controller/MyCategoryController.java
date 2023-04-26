package shop.donutmarket.donut.domain.myCategory.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import shop.donutmarket.donut.domain.myCategory.dto.MyCategoryReq.MyCategoryUpdateReqDTO;
import shop.donutmarket.donut.domain.myCategory.dto.MyCategoryResp.MyCategoryUpdateRespDTO;
import shop.donutmarket.donut.domain.myCategory.dto.MyCategoryResp.defaultMyCategoryRespDTO;
import shop.donutmarket.donut.domain.myCategory.service.MyCategoryService;
import shop.donutmarket.donut.global.auth.MyUserDetails;
import shop.donutmarket.donut.global.dto.ResponseDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/myCategory")
public class MyCategoryController {
    
    private final MyCategoryService myCategoryService;

    @PostMapping("/default")
    public ResponseEntity<?> defaultCategory(@AuthenticationPrincipal MyUserDetails myUserDetails) {
        defaultMyCategoryRespDTO defaultDTO = myCategoryService.디폴트카테고리(myUserDetails);
        return ResponseEntity.ok(defaultDTO);
    }

    @PostMapping
    public ResponseEntity<?> updateCategory(@RequestBody MyCategoryUpdateReqDTO myCategoryUpdateReqDTO, @AuthenticationPrincipal MyUserDetails myUserDetails) {
        MyCategoryUpdateRespDTO updateRespDTO = myCategoryService.카테고리업데이트(myCategoryUpdateReqDTO, myUserDetails);
        return ResponseEntity.ok(updateRespDTO);
    }
    
}
