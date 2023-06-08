package repository;

import entity.News;
import entity.Paraphrase;
import entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParaphraseRepository extends JpaRepository<Paraphrase, Long> {
    Paraphrase getParaphraseById(long id);
    List<News> getParaphraseByNews(News news);
    List<User> getParaphraseByUser(User user);
}
