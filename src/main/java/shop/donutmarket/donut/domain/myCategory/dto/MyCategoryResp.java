package shop.donutmarket.donut.domain.myCategory.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import shop.donutmarket.donut.domain.myCategory.model.MyCategory;

public class MyCategoryResp {
    
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class defaultMyCategoryRespDTO {
        List<MyCategory> list;
    }
}
