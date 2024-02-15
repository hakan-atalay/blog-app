package com.anproject.BlogApp.repository;

import com.anproject.BlogApp.entity.CategoryNews;
import com.anproject.BlogApp.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryNewsRepository extends JpaRepository<CategoryNews, Long> {
    @Query("SELECT cn.news FROM CategoryNews cn WHERE cn.category.id = :categoryId")
    List<News> findNewsByCategoryId(Long categoryId);
}
