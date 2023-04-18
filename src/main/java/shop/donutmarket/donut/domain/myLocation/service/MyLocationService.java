package shop.donutmarket.donut.domain.myLocation.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import shop.donutmarket.donut.domain.myLocation.repository.MyLocationRepository;

@Service
@RequiredArgsConstructor
public class MyLocationService {
    
    private final MyLocationRepository myLocationRepository;
}
