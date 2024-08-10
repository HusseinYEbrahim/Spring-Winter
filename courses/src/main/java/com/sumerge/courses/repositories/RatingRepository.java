package com.sumerge.courses.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sumerge.courses.models.Rating;

public interface RatingRepository extends JpaRepository<Rating, Integer>{
    
}
