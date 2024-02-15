package com.anproject.BlogApp.payload.request;

import com.anproject.BlogApp.entity.CustomUserDetail;
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
public class NewsRequestDto {
    private String title;
    private String contents;
    private String photos;
    private User user;

    public static News mapRequestDtoToEntity(NewsRequestDto requestDto, String fileName, CustomUserDetail user){
        return News.builder()
                .title(requestDto.getTitle())
                .contents(requestDto.getContents())
                .photos(fileName)
                .user(user.getUser())
                .build();
    }

    public static News mapRequestDtoToEntity(NewsRequestDto requestDto){
        return News.builder()
                .title(requestDto.getTitle())
                .contents(requestDto.getContents())
                .photos(requestDto.getPhotos())
                .user(requestDto.getUser())
                .build();
    }

}
