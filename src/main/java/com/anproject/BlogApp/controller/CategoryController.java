package com.anproject.BlogApp.controller;

import com.anproject.BlogApp.payload.request.CategoryRequestDto;
import com.anproject.BlogApp.payload.response.CategoryResponseDto;
import com.anproject.BlogApp.service.CategoryService;
import com.anproject.BlogApp.service.NewsService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CategoryController {

    CategoryService categoryService;
    NewsService newsService;

    public CategoryController(CategoryService categoryService, NewsService newsService) {
        this.categoryService = categoryService;
        this.newsService = newsService;
    }

    @GetMapping("/admin/category/index")
    public String findAllPage(
            @RequestParam(value = "page", required = false, defaultValue = "0") int pageNumber,
            @RequestParam(value = "size", required = false, defaultValue = "5") int size,
            Model model) {
        Page<CategoryResponseDto> categoryPage = categoryService.findByNameContains(PageRequest.of(pageNumber, size));
        model.addAttribute("categories", categoryPage.getContent());
        model.addAttribute("pages", new int[categoryPage.getTotalPages()]);
        model.addAttribute("currentPage", pageNumber);
        model.addAttribute("category", categoryPage);
        return "/admin/category/index";
    }

    @GetMapping("/admin/category/add-category")
    public String showAddCategory(Model model){
        model.addAttribute("category", new CategoryRequestDto());
        return "/admin/category/add-category";
    }

    @PostMapping("/admin/add-category")
    public String addCategory(CategoryRequestDto category){
        categoryService.save(category);
        return "redirect:/admin/category/index";
    }

    @GetMapping("/admin/category/edit-category/{id}")
    public String editCategory(@PathVariable(value = "id") long id, Model model){
        CategoryResponseDto category = categoryService.getById(id);
        model.addAttribute("category", category);
        return "/admin/category/edit-category";
    }

    @GetMapping("/admin/category/edit-delete/{id}")
    public String deleteCategory(@PathVariable(value = "id") long id){
        categoryService.deleteCategory(id);
        return "redirect:/admin/category/index";
    }

    @GetMapping("/news/category-news/{categoryId}")
    public String getCategoryNews(@PathVariable("categoryId") Long categoryId, Model model){
        //List<NewsResponseDto> newsResponseDtoList = newsService.getNewsByCategoryId(categoryId);
        model.addAttribute("newsList", newsService.getNewsByCategoryId(categoryId));
        model.addAttribute("categoryList", categoryService.getAllCategories());
        return "/news/category-news";
    }

}
