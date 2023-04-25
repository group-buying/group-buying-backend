package shop.donutmarket.donut.domain.myCategory.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import shop.donutmarket.donut.domain.admin.model.Category;
import shop.donutmarket.donut.domain.admin.repository.CategoryRepository;
import shop.donutmarket.donut.domain.myCategory.dto.MyCategoryResp.defaultMyCategoryRespDTO;
import shop.donutmarket.donut.domain.myCategory.model.MyCategory;
import shop.donutmarket.donut.domain.myCategory.repository.MyCategoryRepository;
import shop.donutmarket.donut.domain.user.model.User;
import shop.donutmarket.donut.global.auth.MyUserDetails;

@Service
@RequiredArgsConstructor
public class MyCategoryService {
    
    private final MyCategoryRepository myCategoryRepository;
    private final CategoryRepository categoryRepository;

    public defaultMyCategoryRespDTO 디폴트카테고리(@AuthenticationPrincipal MyUserDetails myUserDetails) {
        User user = myUserDetails.getUser();
        List<Long> defaulList = MyCategory.defaultList();
        List<MyCategory> myCategoryList = new ArrayList<>();
        for (Long id : defaulList) {
            Optional<Category> categoryOP = categoryRepository.findById(id);
            Category categoryPS = categoryOP.get();
            MyCategory myCategory = MyCategory.builder().user(user).category(categoryPS).createdAt(LocalDateTime.now()).build();
            myCategoryRepository.save(myCategory);
            myCategoryList.add(myCategory);
        }
        defaultMyCategoryRespDTO defaultDTO = new defaultMyCategoryRespDTO(myCategoryList);
        return defaultDTO;
    }
}
