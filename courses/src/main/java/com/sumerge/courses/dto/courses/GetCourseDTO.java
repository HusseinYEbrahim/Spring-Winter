package com.sumerge.courses.dto.courses;

import java.util.Set;

import com.sumerge.courses.dto.author.AuthorOfCourseDTO;
import com.sumerge.courses.dto.rating.RatingDTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetCourseDTO {

    Integer id;

    String name;

    String description;

    int credit;

    double averageRating;

    Set<RatingDTO> ratings;

    Set<AuthorOfCourseDTO> authors;
    
}
