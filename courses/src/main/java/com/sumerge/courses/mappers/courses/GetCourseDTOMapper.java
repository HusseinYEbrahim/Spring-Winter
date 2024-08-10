package com.sumerge.courses.mappers.courses;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.sumerge.courses.dto.courses.GetCourseDTO;
import com.sumerge.courses.models.Course;

@Mapper(componentModel = "spring")
public interface GetCourseDTOMapper {
    
    Course mapToCourse(GetCourseDTO getCourseDTO);

    @Mapping(target = "averageRating", ignore = true)
    GetCourseDTO mapToDto(Course course);
}
