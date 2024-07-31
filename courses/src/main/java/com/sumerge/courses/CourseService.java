package com.sumerge.courses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sumerge.courses.models.Course;
import com.sumerge.courses.repositories.CourseRepository;

@Component
public class CourseService {

    @Autowired
    CourseRepository courseRepository;

    CourseRecommender courseRecommender;

    public CourseService(CourseRecommender courseRecommender)
    {
        this.courseRecommender = courseRecommender;
    }

    public int addCourse(Course c)
    {
        return courseRepository.save(c);
    }

    public int UpdateCourseDescription(Course c, String description)
    {
        return courseRepository.updateDescription(c.getId(), description);
    }

    public int deleteCourse(Course c)
    {
        return courseRepository.deleteCourse(c.getId());
    }

    public Course viewCourse(String courseId)
    {
        return courseRepository.getById(courseId);
    }

    
}
