package com.anproject.BlogApp.repository;

import com.anproject.BlogApp.entity.Paraphrase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParaphraseRepository extends JpaRepository<Paraphrase, Long> {
    List<Paraphrase> findByNewsId(Long newsId);

}
