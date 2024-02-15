package com.anproject.BlogApp.service;

import com.anproject.BlogApp.entity.CustomUserDetail;
import com.anproject.BlogApp.entity.News;
import com.anproject.BlogApp.payload.request.NewsRequestDto;
import com.anproject.BlogApp.payload.response.NewsResponseDto;
import com.anproject.BlogApp.repository.CategoryNewsRepository;
import com.anproject.BlogApp.repository.NewsRepository;
import com.anproject.BlogApp.util.FileUploadUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NewsService {

    private final NewsRepository newsRepository;
    private final CategoryNewsRepository categoryNewsRepository;

    public NewsService(NewsRepository newsRepository, CategoryNewsRepository categoryNewsRepository) {
        this.newsRepository = newsRepository;
        this.categoryNewsRepository = categoryNewsRepository;
    }

    public NewsResponseDto save(NewsRequestDto newsRequestDto, CustomUserDetail user, MultipartFile multipartFile){
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        News news = NewsRequestDto.mapRequestDtoToEntity(newsRequestDto, fileName, user);
        news.setStatus(false);
        Date createdDate = new Date();
        news.setCreatedDate(createdDate);
        news = newsRepository.save(news);
        NewsResponseDto newsResponseDto = NewsResponseDto.mapEntityToResponseDto(news);
        String uploadDir = "news-photos/" + news.getId();
        try {
            FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return newsResponseDto;
    }

    public List<NewsResponseDto> getNewsByCategoryId(Long categoryId){
        List<News> newsList = categoryNewsRepository.findNewsByCategoryId(categoryId);
        List<NewsResponseDto> newsResponseDtoList = newsList.stream()
                .map(news -> NewsResponseDto.mapEntityToResponseDto(news))
                .collect(Collectors.toList());
        return newsResponseDtoList;
    }

    public NewsResponseDto getById(Long newsId){
        News news = newsRepository.findById(newsId).orElseThrow();
        NewsResponseDto newsResponseDto = NewsResponseDto.mapEntityToResponseDto(news);
        return newsResponseDto;
    }

}
