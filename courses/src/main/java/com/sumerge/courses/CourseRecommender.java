
package com.sumerge.courses;

import java.util.List;

import com.sumerge.courses.models.Course;

public interface CourseRecommender {

    public List<Course> recommend();
    
}