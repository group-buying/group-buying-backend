package shop.donutmarket.donut.domain.myCategory.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class MyCategoryReq {

    @Getter
    @Setter
    public static class MyCategoryUpdateReqDTO {
        List<Long> categoryId;
    }
}
