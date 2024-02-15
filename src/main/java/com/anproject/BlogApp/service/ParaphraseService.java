package com.anproject.BlogApp.service;

import com.anproject.BlogApp.entity.CustomUserDetail;
import com.anproject.BlogApp.entity.News;
import com.anproject.BlogApp.entity.Paraphrase;
import com.anproject.BlogApp.payload.request.ParaphraseRequestDto;
import com.anproject.BlogApp.repository.NewsRepository;
import com.anproject.BlogApp.repository.ParaphraseRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class ParaphraseService {

    private final ParaphraseRepository paraphraseRepository;
    private final NewsRepository newsRepository;

    public ParaphraseService(ParaphraseRepository paraphraseRepository, NewsRepository newsRepository) {
        this.paraphraseRepository = paraphraseRepository;
        this.newsRepository = newsRepository;
    }

    public void save(ParaphraseRequestDto paraphraseRequestDto, CustomUserDetail user, Long newsId){
        Paraphrase paraphrase = ParaphraseRequestDto.mapRequestDtoToEntity(paraphraseRequestDto,user);
        Optional<News> news = newsRepository.findById(newsId);
        paraphrase.setNews(news.get());
        Date createdDate = new Date();
        paraphrase.setCreatedDate(createdDate);
        paraphraseRepository.save(paraphrase);
    }

}
