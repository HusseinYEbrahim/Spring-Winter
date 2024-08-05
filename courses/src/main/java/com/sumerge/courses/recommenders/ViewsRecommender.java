package com.sumerge.courses.recommenders;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.sumerge.courses.CourseRecommender;
import com.sumerge.courses.models.Course;

@Component
public class ViewsRecommender implements CourseRecommender {

    @Override
    public List<Course> recommend() {
        Course bestViewed = new Course("1", "BEST VIEWED", "BEST VIEWED BARDO", 2);
        ArrayList<Course> reccomendations = new ArrayList<>();
        reccomendations.add(bestViewed);
        return reccomendations;
    }

}
