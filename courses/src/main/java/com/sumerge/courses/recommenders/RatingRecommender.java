package com.sumerge.courses.recommenders;

import org.springframework.stereotype.Component;

import com.sumerge.courses.CourseRecommender;

@Component
public class RatingRecommender implements CourseRecommender {

    @Override
    public String recommend() {
        return "This is the best course based on ratings";
    }
    
}
