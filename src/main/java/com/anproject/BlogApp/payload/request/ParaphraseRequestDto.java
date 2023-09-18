package com.anproject.BlogApp.payload.request;

import com.anproject.BlogApp.entity.News;
import com.anproject.BlogApp.entity.Paraphrase;
import com.anproject.BlogApp.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ParaphraseRequestDto {
    private String contents;

    private User user;
    private News news;

    public static Paraphrase mapRequestDtoToEntity(ParaphraseRequestDto requestDto){
        return Paraphrase.builder()
                .contents(requestDto.getContents())
                .user(requestDto.getUser())
                .news(requestDto.getNews())
                .build();
    }
}
