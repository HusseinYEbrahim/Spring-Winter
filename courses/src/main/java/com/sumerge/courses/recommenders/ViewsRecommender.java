package com.sumerge.courses.recommenders;

import org.springframework.stereotype.Component;

import com.sumerge.courses.CourseRecommender;

@Component("viewsRecommender")
public class ViewsRecommender implements CourseRecommender {

    @Override
    public String recommend() {
        return "This is the course with most views";
    }

}
