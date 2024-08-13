package com.sumerge.courses.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sumerge.courses.models.Author;

public interface AuthorRepository extends JpaRepository<Author, Integer>{

    List<Author> findByEmailStartingWith(String emailPrefix);

    Optional<Author> findByName(String username);
    
}
