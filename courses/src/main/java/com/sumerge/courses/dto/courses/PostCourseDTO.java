package com.sumerge.courses.dto.courses;

import java.util.Set;

import com.sumerge.courses.dto.assessment.PostAssessmentDTO;

import lombok.Data;

@Data
public class PostCourseDTO {

    String name;

    String description;

    Integer credit;

    Set<Integer> authorIds;
    
    PostAssessmentDTO assessment;
}
