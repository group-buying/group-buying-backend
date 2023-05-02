package shop.donutmarket.donut.domain.myCategory.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import shop.donutmarket.donut.domain.admin.model.Category;
import shop.donutmarket.donut.domain.admin.repository.CategoryRepository;
import shop.donutmarket.donut.domain.myCategory.dto.MyCategoryReq.MyCategoryUpdateReqDTO;
import shop.donutmarket.donut.domain.myCategory.dto.MyCategoryResp;
import shop.donutmarket.donut.domain.myCategory.dto.MyCategoryResp.MyCategoryUpdateRespDTO;
import shop.donutmarket.donut.domain.myCategory.model.MyCategory;
import shop.donutmarket.donut.domain.myCategory.repository.MyCategoryRepository;
import shop.donutmarket.donut.domain.user.model.User;
import shop.donutmarket.donut.domain.user.repository.UserRepository;
import shop.donutmarket.donut.global.auth.MyUserDetails;
import shop.donutmarket.donut.global.exception.Exception404;
import shop.donutmarket.donut.global.exception.Exception500;

@Service
@RequiredArgsConstructor
public class MyCategoryService {

    private final MyCategoryRepository myCategoryRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    @Transactional
    public MyCategoryResp.DefaultMyCategoryRespDTO 디폴트카테고리(@AuthenticationPrincipal MyUserDetails myUserDetails) {
        Optional<User> userOP = userRepository.findByIdJoinFetch(myUserDetails.getUser().getId());
        if (userOP.isEmpty()) {
            throw new Exception404("존재하지 않는 유저입니다");
        }

        try {
            User userPS = userOP.get();
            List<Long> defaulList = MyCategory.defaultList();
            List<Category> myCategoryList = new ArrayList<>();
            myCategoryRepository.deleteAllByUserId(userPS.getId());
            for (Long id : defaulList) {
                Optional<Category> categoryOP = categoryRepository.findById(id);
                Category categoryPS = categoryOP.get();

                MyCategory myCategory = MyCategory.builder().user(userPS).category(categoryPS).createdAt(LocalDateTime.now()).build();
                myCategoryRepository.save(myCategory);
                myCategoryList.add(categoryPS);
            }
            MyCategoryResp.DefaultMyCategoryRespDTO defaultDTO = new MyCategoryResp.DefaultMyCategoryRespDTO(myCategoryList);
            return defaultDTO;
        } catch (Exception e) {
            throw new Exception500("디폴트카테고리 불러오기 실패 : " + e.getMessage());
        }
    }

    @Transactional
    public MyCategoryUpdateRespDTO 카테고리업데이트(MyCategoryUpdateReqDTO myCategoryUpdateReqDTO, @AuthenticationPrincipal MyUserDetails myUserDetails) {
        Optional<User> userOP = userRepository.findByIdJoinFetch(myUserDetails.getUser().getId());
        if (userOP.isEmpty()) {
            throw new Exception404("존재하지 않는 유저입니다");
        }

        try {
            User userPS = userOP.get();
            List<Long> categoryList;

            // 비어있을경우 디폴트
            if (myCategoryUpdateReqDTO.getCategoryId().isEmpty()) {
                categoryList = MyCategory.defaultList();
            } else {
                categoryList = myCategoryUpdateReqDTO.getCategoryId();
            }

            List<Category> myCategoryList = new ArrayList<>();

            myCategoryRepository.deleteAllByUserId(userPS.getId());
            for (Long id : categoryList) {
                Optional<Category> categoryOP = categoryRepository.findById(id);
                Category categoryPS = categoryOP.get();

                MyCategory myCategory = MyCategory.builder().user(userPS).category(categoryPS).createdAt(LocalDateTime.now()).build();
                myCategoryRepository.save(myCategory);
                myCategoryList.add(categoryPS);
            }
            MyCategoryUpdateRespDTO updateRespDTO = new MyCategoryUpdateRespDTO(myCategoryList);
            return updateRespDTO;
        } catch (Exception e) {
            throw new Exception500("카테고리 업데이트 실패 : " + e.getMessage());
        }
    }
}
