package shop.donutmarket.donut.domain.myCategory.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import shop.donutmarket.donut.domain.admin.model.Category;

public class MyCategoryResp {
    
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DefaultMyCategoryRespDTO {
        List<Category> list;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MyCategoryUpdateRespDTO {
        List<Category> list;
    }
}
