package com.anproject.BlogApp.service;

import com.anproject.BlogApp.entity.CustomUserDetail;
import com.anproject.BlogApp.entity.News;
import com.anproject.BlogApp.payload.request.NewsRequestDto;
import com.anproject.BlogApp.payload.response.NewsResponseDto;
import com.anproject.BlogApp.repository.NewsRepository;
import com.anproject.BlogApp.util.FileUploadUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;

@Service
public class NewsService {

    private final NewsRepository newsRepository;

    public NewsService(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
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

}
