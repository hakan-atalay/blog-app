package com.anproject.BlogApp.payload.request;

import com.anproject.BlogApp.entity.News;
import com.anproject.BlogApp.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewsRequestDto {
    private String title;
    private String contents;
    private String photos;
    private Date createdDate;
    private User user;

    public static News mapRequestDtoToEntity(NewsRequestDto requestDto){
        return News.builder()
                .title(requestDto.getTitle())
                .contents(requestDto.getContents())
                .photos(requestDto.getPhotos())
                .createdDate(requestDto.getCreatedDate())
                .user(requestDto.getUser())
                .build();
    }
}
