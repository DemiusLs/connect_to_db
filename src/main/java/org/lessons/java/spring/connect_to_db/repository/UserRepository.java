package org.lessons.java.spring.connect_to_db.repository;

import java.util.Optional;

import org.lessons.java.spring.connect_to_db.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer>  {

    Optional<User> findByUsername(String username);
} 