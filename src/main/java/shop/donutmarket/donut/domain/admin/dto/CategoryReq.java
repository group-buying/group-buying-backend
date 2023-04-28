package shop.donutmarket.donut.domain.admin.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import shop.donutmarket.donut.domain.admin.model.Category;

public class CategoryReq {
    
    @Getter
    @NoArgsConstructor
    public class CategorySaveReqDTO {
        @NotBlank
        private String name;

        public Category toEntity(){
            return Category.builder().name(name).createdAt(LocalDateTime.now()).build();
        }
    }

}
