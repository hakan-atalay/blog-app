package com.anproject.BlogApp.controller;

import com.anproject.BlogApp.entity.CustomUserDetail;
import com.anproject.BlogApp.payload.request.NewsRequestDto;
import com.anproject.BlogApp.payload.request.ParaphraseRequestDto;
import com.anproject.BlogApp.payload.response.CategoryResponseDto;
import com.anproject.BlogApp.payload.response.NewsResponseDto;
import com.anproject.BlogApp.service.CategoryNewsService;
import com.anproject.BlogApp.service.CategoryService;
import com.anproject.BlogApp.service.NewsService;
import com.anproject.BlogApp.service.ParaphraseService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class NewsController {

    private final NewsService newsService;
    private final CategoryService categoryService;
    private final CategoryNewsService categoryNewsService;
    private final ParaphraseService paraphraseService;

    public NewsController(NewsService newsService, CategoryService categoryService, CategoryNewsService categoryNewsService, ParaphraseService paraphraseService) {
        this.newsService = newsService;
        this.categoryService = categoryService;
        this.categoryNewsService = categoryNewsService;
        this.paraphraseService = paraphraseService;
    }

    @GetMapping("/home/add-news")
    public String showAddNews(Model model) {
        model.addAttribute("news", new NewsRequestDto());
        model.addAttribute("category", new CategoryResponseDto());
        model.addAttribute("categoryList", categoryService.getAllCategories());
        return "home/add-news";
    }

    @PostMapping("/home/add-news-process")
    public String addNewsProcess(@AuthenticationPrincipal CustomUserDetail user,
                                 NewsRequestDto news,
                                 @RequestParam("selectedCategoryId") Long categoryId,
                                 @RequestParam("image") MultipartFile multipartFile) {
       NewsResponseDto savedNews = newsService.save(news, user, multipartFile);
       categoryNewsService.save(savedNews.getId(), categoryId);
       return "redirect:/home/index";
    }

    @GetMapping("/news/news-content/{newsId}")
    public String showNewsContent(@PathVariable("newsId") Long newsId, Model model){
        model.addAttribute("newsContent", categoryNewsService.getById(newsId));
        model.addAttribute("paraphrase", new ParaphraseRequestDto());
        return "news/news-content";
    }

}
