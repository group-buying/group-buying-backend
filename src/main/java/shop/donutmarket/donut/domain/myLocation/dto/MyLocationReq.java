package shop.donutmarket.donut.domain.myLocation.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class MyLocationReq {
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MyLocationSaveReqDTO {
        @NotBlank
        private String state;
        @NotBlank
        private String city;
        @NotBlank
        private String town;
    }
}
