
package com.sumerge.courses.recommenders;

import java.util.List;

import com.sumerge.courses.dto.courses.GetCourseDTO;

public interface CourseRecommender {

    public List<GetCourseDTO> recommend();
    
}