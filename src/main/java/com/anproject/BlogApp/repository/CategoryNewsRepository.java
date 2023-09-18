package com.anproject.BlogApp.repository;

import com.anproject.BlogApp.entity.Category;
import com.anproject.BlogApp.entity.CategoryNews;
import com.anproject.BlogApp.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryNewsRepository extends JpaRepository<CategoryNews, Long> {
}
