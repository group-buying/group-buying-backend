package shop.donutmarket.donut.domain.myCategory.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;
import shop.donutmarket.donut.domain.myCategory.dto.MyCategoryReq.MyCategoryUpdateReqDTO;
import shop.donutmarket.donut.domain.myCategory.dto.MyCategoryResp;
import shop.donutmarket.donut.domain.myCategory.dto.MyCategoryResp.MyCategoryUpdateRespDTO;
import shop.donutmarket.donut.domain.myCategory.service.MyCategoryService;
import shop.donutmarket.donut.global.auth.MyUserDetails;
import shop.donutmarket.donut.global.dto.ResponseDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/myCategories")
public class MyCategoryController {
    
    private final MyCategoryService myCategoryService;

    @PostMapping("/default")
    public ResponseEntity<?> defaultCategory(@AuthenticationPrincipal MyUserDetails myUserDetails) {
        MyCategoryResp.DefaultMyCategoryRespDTO defaultDTO = myCategoryService.디폴트카테고리(myUserDetails);
        ResponseDTO<?> responseDTO = new ResponseDTO<>(defaultDTO);
        return ResponseEntity.ok(responseDTO);
    }

    @PostMapping
    public ResponseEntity<?> updateCategory(@RequestBody MyCategoryUpdateReqDTO myCategoryUpdateReqDTO, @AuthenticationPrincipal MyUserDetails myUserDetails, BindingResult bindingResult) {
        MyCategoryUpdateRespDTO updateRespDTO = myCategoryService.카테고리업데이트(myCategoryUpdateReqDTO, myUserDetails);
        ResponseDTO<?> responseDTO = new ResponseDTO<>(updateRespDTO);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping
    public ResponseEntity<?> myCategory(@AuthenticationPrincipal MyUserDetails myUserDetails, BindingResult bindingResult) {
        MyCategoryResp.MyCategorySelectRespDTO myCategorySelectRespDTO  = myCategoryService.나의카테고리보기(myUserDetails);
        ResponseDTO<?> responseDTO = new ResponseDTO<>(myCategorySelectRespDTO);
        return ResponseEntity.ok(responseDTO);
    }
}
