package shop.donutmarket.donut.domain.myLocation.controller;

import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import shop.donutmarket.donut.domain.myLocation.service.MyLocationService;

@RestController
@RequiredArgsConstructor
public class MyLocationController {
    
    private final MyLocationService myLocationService;
}
