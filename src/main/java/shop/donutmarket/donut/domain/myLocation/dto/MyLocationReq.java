package shop.donutmarket.donut.domain.myLocation.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

public class MyLocationReq {
    @Getter
    @Setter
    public static class MyLocationSaveReqDTO {
        @NotBlank(message = "광역시 또는 도를 입력해주세요.")
        private String state;
        @NotBlank(message = "시 또는 군 또는 자치구를 입력해주세요.")
        private String city;
        @NotBlank(message = "행정시,구 또는 읍,면,동을 입력해주세요.")
        private String town;
    }
}
