package repository;

import entity.Category;
import entity.CategoryNews;
import entity.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryNewsRepository extends JpaRepository<CategoryNews, Long> {
    List<CategoryNews> getCategoryNewsByCategory(Category category);
    List<CategoryNews> getCategoryNewsByNews(News news);
}
