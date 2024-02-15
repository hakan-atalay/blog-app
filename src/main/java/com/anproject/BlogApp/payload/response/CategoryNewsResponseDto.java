package com.anproject.BlogApp.payload.response;

import com.anproject.BlogApp.entity.Category;
import com.anproject.BlogApp.entity.CategoryNews;
import com.anproject.BlogApp.entity.News;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryNewsResponseDto {
    private Long id;
    private News news;
    private Category category;

    public static CategoryNewsResponseDto mapEntitytoResponseDto(CategoryNews categoryNews){
        return CategoryNewsResponseDto.builder()
                .id(categoryNews.getId())
                .news(categoryNews.getNews())
                .category(categoryNews.getCategory())
                .build();
    }

}
