package com.anproject.BlogApp.service;

import com.anproject.BlogApp.entity.News;
import com.anproject.BlogApp.payload.request.NewsRequestDto;
import com.anproject.BlogApp.repository.NewsRepository;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class NewsService {

    private NewsRepository newsRepository;

    public NewsService(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    public News save(NewsRequestDto newsRequestDto){
        News news = NewsRequestDto.mapRequestDtoToEntity(newsRequestDto);
        news.setTitle(newsRequestDto.getTitle());
        news.setContents(newsRequestDto.getContents());
        news.setPhotos(newsRequestDto.getPhotos());
        news.setStatus(false);
        Date cdate = new Date();
        news.setCreatedDate(cdate);
        news.setUser(newsRequestDto.getUser());
        news = newsRepository.save(news);
        return news;
    }

}
