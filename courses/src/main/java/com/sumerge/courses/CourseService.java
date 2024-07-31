package com.sumerge.courses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.sumerge.courses.models.Course;

@Component
public class CourseService {

    @Autowired
    JdbcTemplate jdbcTemplate;

    CourseRecommender courseRecommender;

    public CourseService(CourseRecommender courseRecommender)
    {
        this.courseRecommender = courseRecommender;
    }

    public boolean addCourse(Course c)
    {
        String sql = "select * from messi;";
        jdbcTemplate.execute(sql);
        return true;
    }

    
}
