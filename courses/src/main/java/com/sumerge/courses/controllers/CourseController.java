package com.sumerge.courses.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sumerge.courses.CourseService;
import com.sumerge.courses.models.Course;

@RestController
public class CourseController {
    
    @Autowired
    CourseService courseService;

    @PostMapping("/courses/add")
    public String add(@RequestBody Course course)
    {
        int affectedRows = courseService.addCourse(course);
        return affectedRows + " rows are affected";
    }

    @GetMapping("/courses/view/{id}")
    public Course view(@PathVariable String id)
    {
        Course course = courseService.viewCourse(id);
        return course;
    }

    @PutMapping("/courses/update/description/{id}")
    public String updateCourseDescription(@PathVariable String id, @RequestBody String description)
    {
        int affectedRows = courseService.UpdateCourseDescription(id, description);
        return affectedRows + " rows are affected";
    }

    @DeleteMapping("/courses/delete/{id}")
    public String delete(@PathVariable String id)
    {
        int affectedRows = courseService.deleteCourse(id);
        return affectedRows + " are affected";
    }

    @GetMapping("/courses")
    public List<Course> viewAll()
    {
        return courseService.viewAllCourses();
    }

    @GetMapping("/courses/discover")
    public List<Course> getRecommendations()
    {
        return courseService.getCourseRecommender().recommend();
    }
}
