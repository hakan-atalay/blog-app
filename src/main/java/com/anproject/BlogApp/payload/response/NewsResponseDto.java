package com.anproject.BlogApp.payload.response;

import com.anproject.BlogApp.entity.News;
import com.anproject.BlogApp.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewsResponseDto {
    private Long id;
    private String title;
    private String contents;
    private String photos;
    private User user;

    public static NewsResponseDto mapEntityToResponseDto(News news){
        return NewsResponseDto.builder()
                .id(news.getId())
                .title(news.getTitle())
                .contents(news.getContents())
                .photos(news.getPhotosImagePath())
                .user(news.getUser())
                .build();
    }

}
