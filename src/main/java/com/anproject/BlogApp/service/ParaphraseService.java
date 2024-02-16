package com.anproject.BlogApp.service;

import com.anproject.BlogApp.entity.CustomUserDetail;
import com.anproject.BlogApp.entity.News;
import com.anproject.BlogApp.entity.Paraphrase;
import com.anproject.BlogApp.payload.request.ParaphraseRequestDto;
import com.anproject.BlogApp.payload.response.ParaphraseResponseDto;
import com.anproject.BlogApp.repository.NewsRepository;
import com.anproject.BlogApp.repository.ParaphraseRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public List<ParaphraseResponseDto> getByNewsId(Long newsId){
        List<Paraphrase> paraphraseList = paraphraseRepository.findByNewsId(newsId);
        List<ParaphraseResponseDto> paraphraseResponseDtoList = paraphraseList.stream()
                .map(paraphrase -> ParaphraseResponseDto.mapEntityToReponseDto(paraphrase))
                .collect(Collectors.toList());
        return paraphraseResponseDtoList;
    }

}
