package shop.donutmarket.donut.domain.myLocation.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import shop.donutmarket.donut.domain.myLocation.dto.MyLocationReq.MyLocationSaveReqDTO;
import shop.donutmarket.donut.domain.myLocation.dto.MyLocationResp.DefaultMyLocationRespDTO;
import shop.donutmarket.donut.domain.myLocation.dto.MyLocationResp.MyLocationSaveRespDTO;
import shop.donutmarket.donut.domain.myLocation.model.MyLocation;
import shop.donutmarket.donut.domain.myLocation.repository.MyLocationRepository;
import shop.donutmarket.donut.domain.user.model.User;
import shop.donutmarket.donut.global.auth.MyUserDetails;

@Service
@RequiredArgsConstructor
public class MyLocationService {
    
    private final MyLocationRepository myLocationRepository;

    public DefaultMyLocationRespDTO 디폴트지역(@AuthenticationPrincipal MyUserDetails myUserDetails) {
        User user = myUserDetails.getUser();
        Optional<MyLocation> myLocationOP = myLocationRepository.findByUserId(user.getId());
        if (!(myLocationOP.isPresent())) {
            MyLocation myLocation = MyLocation.builder().user(user).state("부산광역시")
            .city("부산진구").town("부전동").createdAt(LocalDateTime.now()).build();
            myLocationRepository.save(myLocation);

            DefaultMyLocationRespDTO defaultLocationDTO = new DefaultMyLocationRespDTO(
            "부산광역시", "부산진구", "부전동", LocalDateTime.now());
            return defaultLocationDTO;
        }

        MyLocation myLocationPS = myLocationOP.get();
        myLocationPS.defaultLocation();
        DefaultMyLocationRespDTO defaultLocationDTO = new DefaultMyLocationRespDTO(
            myLocationPS.getState(), myLocationPS.getCity(), myLocationPS.getTown(), myLocationPS.getCreatedAt()); 
        return defaultLocationDTO;
    }

    public MyLocationSaveRespDTO 내지역변경(MyLocationSaveReqDTO myLocationSaveReqDTO, @AuthenticationPrincipal MyUserDetails myUserDetails) {
        User user = myUserDetails.getUser();
        Optional<MyLocation> myLocationOP = myLocationRepository.findByUserId(user.getId());
        MyLocation myLocationPS = myLocationOP.get();
        myLocationPS.updateMyLocation(myLocationSaveReqDTO.getState(), myLocationSaveReqDTO.getCity(),
        myLocationSaveReqDTO.getTown(), LocalDateTime.now());
        MyLocationSaveRespDTO saveRespDTO = new MyLocationSaveRespDTO(myLocationSaveReqDTO.getState(), myLocationSaveReqDTO.getCity(),
        myLocationSaveReqDTO.getTown(), LocalDateTime.now());
        return saveRespDTO;
    }
}
