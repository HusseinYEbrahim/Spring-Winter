package com.sumerge.courses.recommenders;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import com.sumerge.courses.CourseRecommender;

@Component
@Primary
public class RatingRecommender implements CourseRecommender {

    @Override
    public String recommend() {
        return "This is the best course based on ratings";
    }
    
}
