package repository;

import entity.Media;
import entity.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MediaRepository extends JpaRepository<Media, Long> {
    Media getMediaById(long id);
    List<News> getMediaByNews(News news);
}
