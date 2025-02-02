package com.sumerge.courses.recommenders;

import org.springframework.stereotype.Component;

import com.sumerge.courses.CourseRecommender;

@Component
public class ViewsRecommender implements CourseRecommender {

    @Override
    public String recommend() {
        return "This is the course with most views";
    }

}
