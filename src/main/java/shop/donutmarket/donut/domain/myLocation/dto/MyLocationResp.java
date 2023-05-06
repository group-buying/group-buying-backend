package shop.donutmarket.donut.domain.myLocation.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import shop.donutmarket.donut.domain.myLocation.model.MyLocation;
import shop.donutmarket.donut.domain.user.model.User;

public class MyLocationResp {

    @Getter
    @Setter
    public static class DefaultMyLocationRespDTO {
        private String state;
        private String city;
        private String town;
        private User user;
        private LocalDateTime createdAt;

        public DefaultMyLocationRespDTO(String state, String city, String town, User user, LocalDateTime createdAt) {
            this.state = state;
            this.city = city;
            this.town = town;
            this.user = user;
            this.createdAt = createdAt;
        }
    }

    @Getter
    @Setter
    public static class MyLocationSaveRespDTO {
        private String state;
        private String city;
        private String town;
        private User user;
        private LocalDateTime createdAt;

        public MyLocationSaveRespDTO(String state, String city, String town, User user, LocalDateTime createdAt) {
            this.state = state;
            this.city = city;
            this.town = town;
            this.user = user;
            this.createdAt = createdAt;
        }
    }

    @Getter
    @Setter
    public static class MyLocationSelectRespDTO {
        private MyLocation myLocation;

        public MyLocationSelectRespDTO(MyLocation myLocation) {
            this.myLocation = myLocation;
        }
    }
}
