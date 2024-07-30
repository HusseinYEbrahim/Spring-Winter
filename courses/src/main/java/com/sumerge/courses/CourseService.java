package com.sumerge.courses;

import org.springframework.beans.factory.annotation.Autowired;

public class CourseService {

    CourseRecommender courseRecommender;

    @Autowired
    public void setCourseRecommender(CourseRecommender courseRecommender)
    {
        this.courseRecommender = courseRecommender;
    }

    
}
