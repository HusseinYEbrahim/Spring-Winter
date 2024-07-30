package com.sumerge.courses.recommenders;

import com.sumerge.courses.CourseRecommender;

public class RatingRecommender implements CourseRecommender {

    @Override
    public String recommend() {
        return "This is the best course based on ratings";
    }
    
}
