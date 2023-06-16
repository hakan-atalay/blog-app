package com.anproject.BlogApp.repository;

import com.anproject.BlogApp.entity.Media;
import com.anproject.BlogApp.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MediaRepository extends JpaRepository<Media, Long> {
    Media getMediaById(long id);
    List<News> getMediaByNews(News news);
}
