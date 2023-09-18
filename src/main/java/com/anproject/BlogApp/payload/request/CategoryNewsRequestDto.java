package com.anproject.BlogApp.payload.request;

import com.anproject.BlogApp.entity.Category;
import com.anproject.BlogApp.entity.News;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryNewsRequestDto {

    private Category category;
    private News news;

    public static CategoryNewsRequestDto mapRequestDtoToEntity(CategoryNewsRequestDto requestDto){
        return CategoryNewsRequestDto.builder()
                .news(requestDto.getNews())
                .category(requestDto.getCategory())
                .build();
    }
}
