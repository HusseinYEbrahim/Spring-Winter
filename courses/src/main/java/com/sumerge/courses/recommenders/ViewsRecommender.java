package com.sumerge.courses.recommenders;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.sumerge.courses.dto.courses.GetCourseDTO;

@Component
public class ViewsRecommender implements CourseRecommender {

    @Override
    public List<GetCourseDTO> recommend() {
        
        GetCourseDTO bestViewed = GetCourseDTO.builder().description("").authors(null)
            .credit(2).name("Math3").ratings(null).build();
        ArrayList<GetCourseDTO> reccomendations = new ArrayList<>();
        reccomendations.add(bestViewed);
        return reccomendations;
    }

}
