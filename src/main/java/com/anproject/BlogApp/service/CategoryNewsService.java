package com.anproject.BlogApp.service;

import com.anproject.BlogApp.entity.Category;
import com.anproject.BlogApp.entity.CategoryNews;
import com.anproject.BlogApp.entity.News;
import com.anproject.BlogApp.repository.CategoryNewsRepository;
import com.anproject.BlogApp.repository.CategoryRepository;
import com.anproject.BlogApp.repository.NewsRepository;
import org.springframework.stereotype.Service;

@Service
public class CategoryNewsService {

    private final CategoryNewsRepository categoryNewsRepository;
    private final NewsRepository newsRepository;
    private final CategoryRepository categoryRepository;

    public CategoryNewsService(CategoryNewsRepository categoryNewsRepository, NewsRepository newsRepository, CategoryRepository categoryRepository) {
        this.categoryNewsRepository = categoryNewsRepository;
        this.newsRepository = newsRepository;
        this.categoryRepository = categoryRepository;
    }

    public void save(Long newsId, Long categoryId){
        News news = newsRepository.findById(newsId).orElseThrow();
        Category category = categoryRepository.findById(categoryId).orElseThrow();
        CategoryNews categoryNews = new CategoryNews(category, news);
        categoryNewsRepository.save(categoryNews);
    }

}
