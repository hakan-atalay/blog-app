package repository;

import entity.News;
import entity.Slider;
import entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SliderRepository extends JpaRepository<Slider, Long> {
    Slider getSliderById(long id);
    List<News> getSliderByNews(News news);
    List<User> getSliderByUser(User user);
}
