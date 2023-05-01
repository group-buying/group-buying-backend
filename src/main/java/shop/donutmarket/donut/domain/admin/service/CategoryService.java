package shop.donutmarket.donut.domain.admin.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import shop.donutmarket.donut.domain.admin.dto.CategoryReq.CategoryDeleteReqDTO;
import shop.donutmarket.donut.domain.admin.dto.CategoryReq.CategorySaveReqDTO;
import shop.donutmarket.donut.domain.admin.model.Category;
import shop.donutmarket.donut.domain.admin.repository.CategoryRepository;
import shop.donutmarket.donut.global.exception.Exception404;
import shop.donutmarket.donut.global.exception.Exception500;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Transactional(readOnly = true)
    public List<Category> 카테고리조회() {
        List<Category> list = categoryRepository.findAll();
        return list;
    }
    
    @Transactional
    public Category 카테고리추가(CategorySaveReqDTO categorySaveReqDTO) {
        Category category = categorySaveReqDTO.toEntity();
        try {
            categoryRepository.save(category);
            return category;
        } catch (Exception e) {
            throw new Exception500("카테고리를 추가하는데 실패했습니다.");
        }
    }
    
    @Transactional
    public void 카테고리제거(CategoryDeleteReqDTO CategoryDeleteReqDTO) {
        Optional<Category> categoryOP = categoryRepository.findById(CategoryDeleteReqDTO.getId());
        
        if (!(categoryOP.isPresent())) {
            throw new Exception404("존재하지 않는 카테고리입니다.");
        }
        try {
            categoryRepository.deleteById(CategoryDeleteReqDTO.getId());
            // 삭제된 해당 카테고리를 가지고 있는 내카테고리들을 업데이트 해줘야 함.
        } catch (Exception e) {
           throw new Exception500("카테고리 삭제에 실패 했습니다.");
        }
    }
}
