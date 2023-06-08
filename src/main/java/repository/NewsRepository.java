package repository;

import entity.News;
import entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewsRepository extends JpaRepository<News, Long> {
    News getNewsById(long id);
    List<User> getNewsByUser(User user);
}
