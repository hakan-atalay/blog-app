package com.anproject.BlogApp.controller;

import com.anproject.BlogApp.entity.CustomUserDetail;
import com.anproject.BlogApp.entity.News;
import com.anproject.BlogApp.payload.request.NewsRequestDto;
import com.anproject.BlogApp.service.CategoryNewsService;
import com.anproject.BlogApp.service.CategoryService;
import com.anproject.BlogApp.service.NewsService;
import com.anproject.BlogApp.util.FileUploadUtil;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class NewsController {

    private final NewsService newsService;
    private final CategoryService categoryService;
    private final CategoryNewsService categoryNewsService;

    public NewsController(NewsService newsService, CategoryService categoryService, CategoryNewsService categoryNewsService) {
        this.newsService = newsService;
        this.categoryService = categoryService;
        this.categoryNewsService = categoryNewsService;
    }

    @GetMapping("/home/add-news")
    public String showAddNews(Model model) {
        model.addAttribute("news", new NewsRequestDto());
        return "home/add-news";
    }

    @PostMapping("/home/add-news-process")
    public String addNewsProcess(@AuthenticationPrincipal CustomUserDetail user, NewsRequestDto news, @RequestParam("image") MultipartFile multipartFile) throws IOException {
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        news.setPhotos(fileName);
        news.setUser(user.getUser());
        News savedNews = newsService.save(news);
        String uploadDir = "news-photos/" + savedNews.getId();
        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
        return "redirect:/home/index";
    }

}
