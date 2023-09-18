package com.anproject.BlogApp.repository;

import com.anproject.BlogApp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User getUserByNickname(String nickname);

}
