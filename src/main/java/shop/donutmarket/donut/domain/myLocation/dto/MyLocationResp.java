package shop.donutmarket.donut.domain.myLocation.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class MyLocationResp {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DefaultMyLocationRespDTO {
        private String state;
        private String city;
        private String town;
        private LocalDateTime createdAt;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MyLocationSaveRespDTO {
        private String state;
        private String city;
        private String town;
        private LocalDateTime createdAt;
    }
}
