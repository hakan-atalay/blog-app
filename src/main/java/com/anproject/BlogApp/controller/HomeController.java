package com.anproject.BlogApp.controller;

import com.anproject.BlogApp.service.CategoryNewsService;
import com.anproject.BlogApp.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    CategoryNewsService categoryNewsService;
    CategoryService categoryService;

    public HomeController(CategoryNewsService categoryNewsService, CategoryService categoryService) {
        this.categoryNewsService = categoryNewsService;
        this.categoryService = categoryService;
    }

    @GetMapping(value={"", "/", "home/index"})
    public String homePage(Model model){
        model.addAttribute("categoryNewsList", categoryNewsService.getAllCategoryNews());
        model.addAttribute("categoryList", categoryService.getAllCategories());
        return "/home/index";
    }

}
