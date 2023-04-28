package shop.donutmarket.donut.domain.admin.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import shop.donutmarket.donut.domain.admin.dto.CategoryReq.CategoryDeleteReqDTO;
import shop.donutmarket.donut.domain.admin.dto.CategoryReq.CategorySaveReqDTO;
import shop.donutmarket.donut.domain.admin.model.Category;
import shop.donutmarket.donut.domain.admin.service.CategoryService;
import shop.donutmarket.donut.global.auth.MyUserDetails;
import shop.donutmarket.donut.global.dto.ResponseDTO;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final HttpSession session;
    
    private final CategoryService categoryService;

    @GetMapping("/category")
    public String category(@AuthenticationPrincipal MyUserDetails myUserDetails) {
        List<Category> categoryList = categoryService.카테고리조회();
        session.setAttribute("categoryList", categoryList);
        return "admin/category";
    }

    @PostMapping("/category")
    public ResponseEntity<?> addCategory(@RequestBody @Valid CategorySaveReqDTO categorySaveReqDTO, @AuthenticationPrincipal MyUserDetails myUserDetails) {
        Category addCategory = categoryService.카테고리추가(categorySaveReqDTO);
        return new ResponseEntity<>(new ResponseDTO<>().data(addCategory) , HttpStatus.CREATED);
    }
    
    @DeleteMapping("/category")
    public ResponseEntity<?> deleteCategory(@RequestBody @Valid CategoryDeleteReqDTO categoryDeleteReqDTO, @AuthenticationPrincipal MyUserDetails myUserDetails) {
        categoryService.카테고리제거(categoryDeleteReqDTO);
        return new ResponseEntity<>(new ResponseDTO<>(), HttpStatus.OK);
    }
}