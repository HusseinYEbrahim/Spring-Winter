package com.sumerge.courses;

import java.util.List;

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

    public int UpdateCourseDescription(String id, String description)
    {
        return courseRepository.updateDescription(id, description);
    }

    public int deleteCourse(String id)
    {
        return courseRepository.deleteCourse(id);
    }

    public Course viewCourse(String courseId)
    {
        return courseRepository.getById(courseId);
    }

    public List<Course> viewAllCourses()
    {
        return courseRepository.getAll();
    }

    
}
