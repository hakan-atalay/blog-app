package com.anproject.BlogApp.service;

import com.anproject.BlogApp.entity.Category;
import com.anproject.BlogApp.entity.CategoryNews;
import com.anproject.BlogApp.entity.News;
import com.anproject.BlogApp.payload.response.CategoryNewsResponseDto;
import com.anproject.BlogApp.repository.CategoryNewsRepository;
import com.anproject.BlogApp.repository.CategoryRepository;
import com.anproject.BlogApp.repository.NewsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    public List<CategoryNewsResponseDto> getAllCategoryNews(){
        List<CategoryNews> categoryNewsList = categoryNewsRepository.findAll();
        List<CategoryNewsResponseDto> categoryNewsResponseDtoList = categoryNewsList.stream()
                .map(CategoryNews -> CategoryNewsResponseDto.mapEntitytoResponseDto(CategoryNews))
                .collect(Collectors.toList());
        return categoryNewsResponseDtoList;
    }

    public CategoryNewsResponseDto getById(Long id){
        CategoryNews categoryNews = categoryNewsRepository.findById(id).orElseThrow();
        CategoryNewsResponseDto categoryNewsResponseDto = CategoryNewsResponseDto
                .mapEntitytoResponseDto(categoryNews);
        return categoryNewsResponseDto;
    }

}
