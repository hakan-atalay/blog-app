package com.anproject.BlogApp.service;

import com.anproject.BlogApp.payload.request.CategoryNewsRequestDto;
import com.anproject.BlogApp.repository.CategoryNewsRepository;
import org.springframework.stereotype.Service;

@Service
public class CategoryNewsService {

    private CategoryNewsRepository categoryNewsRepository;

    public CategoryNewsService(CategoryNewsRepository categoryNewsRepository) {
        this.categoryNewsRepository = categoryNewsRepository;
    }

    public void save(CategoryNewsRequestDto categoryNewsRequestDto){
        CategoryNewsRequestDto categoryNews = CategoryNewsRequestDto.mapRequestDtoToEntity(categoryNewsRequestDto);
        categoryNews.setCategory(categoryNewsRequestDto.getCategory());
        categoryNews.setNews(categoryNewsRequestDto.getNews());
    }

}
