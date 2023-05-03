package shop.donutmarket.donut.domain.myCategory.dto;

import java.util.List;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

public class MyCategoryReq {

    @Getter
    @Setter
    public static class MyCategoryUpdateReqDTO {
        @NotNull(message = "카테고리 목록을 입력해주세요.")
        List<Long> categoryId;
    }
}
