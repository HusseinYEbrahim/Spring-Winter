package com.sumerge.courses.recommenders;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import com.sumerge.courses.dto.courses.GetCourseDTO;
import com.sumerge.courses.mappers.courses.GetCourseDTOMapper;
import com.sumerge.courses.models.Course;
import com.sumerge.courses.repositories.CourseRepository;
import com.sumerge.courses.services.RatingService;

@Component
@Primary
public class RatingRecommender implements CourseRecommender {

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    RatingService ratingService;

    @Autowired
    GetCourseDTOMapper getCourseDTOMapper;

    @Override
    public List<GetCourseDTO> recommend() {
        List<Course> topThree = courseRepository.getBestThreeRatings();
        List<GetCourseDTO> getCourseDTOs = new ArrayList<>();
        topThree.forEach((c) -> {
            GetCourseDTO getCourseDTO = getCourseDTOMapper.mapToDto(c);
            getCourseDTOs.add(getCourseDTO);
            getCourseDTO.setAverageRating(ratingService.getAverageRating(c.getRatings()));
        });
        return getCourseDTOs;
    }
    
}
