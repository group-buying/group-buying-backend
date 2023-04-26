package shop.donutmarket.donut.domain.myLocation.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import shop.donutmarket.donut.domain.myLocation.dto.MyLocationReq.MyLocationSaveReqDTO;
import shop.donutmarket.donut.domain.myLocation.dto.MyLocationResp.DefaultMyLocationRespDTO;
import shop.donutmarket.donut.domain.myLocation.dto.MyLocationResp.MyLocationSaveRespDTO;
import shop.donutmarket.donut.domain.myLocation.model.MyLocation;
import shop.donutmarket.donut.domain.myLocation.repository.MyLocationRepository;
import shop.donutmarket.donut.domain.user.model.User;
import shop.donutmarket.donut.global.auth.MyUserDetails;
import shop.donutmarket.donut.global.exception.Exception404;
import shop.donutmarket.donut.global.exception.Exception500;

@Service
@RequiredArgsConstructor
public class MyLocationService {

    private final MyLocationRepository myLocationRepository;

    @Transactional
    public DefaultMyLocationRespDTO 디폴트지역(@AuthenticationPrincipal MyUserDetails myUserDetails) {
        User user = myUserDetails.getUser();
        Optional<MyLocation> myLocationOP = myLocationRepository.findByUserId(user.getId());
        try {
            // 내 지역이 있을 경우, 디폴트로 업데이트
            if (myLocationOP.isPresent()) {
                MyLocation myLocationPS = myLocationOP.get();
                myLocationPS.defaultLocation();
                DefaultMyLocationRespDTO defaultLocationDTO = new DefaultMyLocationRespDTO(
                        myLocationPS.getState(), myLocationPS.getCity(), myLocationPS.getTown(), myLocationPS.getCreatedAt());
                return defaultLocationDTO;
            }
            // 내 지역이 없을 경우, 디폴트로 저장
            MyLocation myLocation = MyLocation.builder().user(user).state("부산광역시")
                    .city("부산진구").town("부전동").createdAt(LocalDateTime.now()).build();
            myLocationRepository.save(myLocation);

            DefaultMyLocationRespDTO defaultLocationDTO = new DefaultMyLocationRespDTO(
                    "부산광역시", "부산진구", "부전동", LocalDateTime.now());
            return defaultLocationDTO;

        } catch (Exception e) {
            throw new Exception500("디폴트 지역 설정 실패 : " + e.getMessage());
        }
    }

    @Transactional
    public MyLocationSaveRespDTO 내지역변경(MyLocationSaveReqDTO myLocationSaveReqDTO, @AuthenticationPrincipal MyUserDetails myUserDetails) {
        User user = myUserDetails.getUser();
        Optional<MyLocation> myLocationOP = myLocationRepository.findByUserId(user.getId());
        if (myLocationOP.isEmpty()) {
            throw new Exception404("내 지역을 설정하지 않았습니다");
        }
        try {
            MyLocation myLocationPS = myLocationOP.get();

            myLocationPS.updateMyLocation(myLocationSaveReqDTO.getState(), myLocationSaveReqDTO.getCity(),
                    myLocationSaveReqDTO.getTown(), LocalDateTime.now());

            MyLocationSaveRespDTO saveRespDTO = new MyLocationSaveRespDTO(myLocationSaveReqDTO.getState(), myLocationSaveReqDTO.getCity(),
                    myLocationSaveReqDTO.getTown(), LocalDateTime.now());

            return saveRespDTO;
        } catch (Exception e) {
            throw new Exception500("내 지역 변경 실패 : " + e.getMessage());
        }
    }
}
