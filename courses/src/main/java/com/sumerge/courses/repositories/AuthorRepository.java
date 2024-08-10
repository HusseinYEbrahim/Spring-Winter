package com.sumerge.courses.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sumerge.courses.models.Author;

public interface AuthorRepository extends JpaRepository<Author, Integer>{

    List<Author> findByEmailStartingWith(String emailPrefix);
    
}
