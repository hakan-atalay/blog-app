package com.anproject.BlogApp.payload.response;

import com.anproject.BlogApp.entity.News;
import com.anproject.BlogApp.entity.Paraphrase;
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
public class ParaphraseResponseDto {
    private Long id;
    private String contents;

    private News news;
    private User user;

    public static ParaphraseResponseDto mapEntityToReponseDto(Paraphrase paraphrase){
        return ParaphraseResponseDto.builder()
                .id(paraphrase.getId())
                .contents(paraphrase.getContents())
                .news(paraphrase.getNews())
                .user(paraphrase.getUser())
                .build();
    }
}
