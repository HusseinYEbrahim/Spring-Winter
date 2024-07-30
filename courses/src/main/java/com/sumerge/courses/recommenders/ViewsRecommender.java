package com.sumerge.courses.recommenders;

import com.sumerge.courses.CourseRecommender;

public class ViewsRecommender implements CourseRecommender {

    @Override
    public String recommend() {
        return "This is the course with most views";
    }

}
