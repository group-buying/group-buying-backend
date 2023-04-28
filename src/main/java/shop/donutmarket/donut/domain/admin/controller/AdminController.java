package shop.donutmarket.donut.domain.admin.controller;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import shop.donutmarket.donut.domain.admin.model.Category;
import shop.donutmarket.donut.domain.admin.service.CategoryService;
import shop.donutmarket.donut.global.auth.MyUserDetails;

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

}