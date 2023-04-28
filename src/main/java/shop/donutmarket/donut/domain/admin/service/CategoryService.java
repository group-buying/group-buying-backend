package shop.donutmarket.donut.domain.admin.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import shop.donutmarket.donut.domain.admin.dto.CategoryReq.CategorySaveReqDTO;
import shop.donutmarket.donut.domain.admin.model.Category;
import shop.donutmarket.donut.domain.admin.repository.CategoryRepository;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<Category> 카테고리조회() {
        List<Category> list = categoryRepository.findAll();
        return list;
    }
    
    public Category 카테고리추가(CategorySaveReqDTO categorySaveReqDTO) {
        Category category = categorySaveReqDTO.toEntity();
        categoryRepository.save(category);
        return category;
    }
}
