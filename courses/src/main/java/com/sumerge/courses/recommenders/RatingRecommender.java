package com.sumerge.courses.recommenders;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import com.sumerge.courses.CourseRecommender;
import com.sumerge.courses.models.Course;
import com.sumerge.courses.repositories.CourseRepository;

@Component
@Primary
public class RatingRecommender implements CourseRecommender {

    @Autowired
    CourseRepository courseRepository;

    @Override
    public List<Course> recommend() {
        return courseRepository.getBestThreeRatings();
    }
    
}
