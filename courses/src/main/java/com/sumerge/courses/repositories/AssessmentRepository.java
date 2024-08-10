package com.sumerge.courses.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sumerge.courses.models.Assessment;

public interface AssessmentRepository extends JpaRepository<Assessment, Integer>{
    
}
