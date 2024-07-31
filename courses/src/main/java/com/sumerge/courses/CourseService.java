package com.sumerge.courses;

import org.springframework.stereotype.Component;

@Component
public class CourseService {

    CourseRecommender courseRecommender;

    public CourseService(CourseRecommender courseRecommender)
    {
        this.courseRecommender = courseRecommender;
    }

    
}
