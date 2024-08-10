package com.sumerge.courses.mappers.courses;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.sumerge.courses.dto.courses.CourseOfAuthorDTO;
import com.sumerge.courses.models.Course;

@Mapper(componentModel = "spring")
public interface CourseOfAuthorDTOMapper {
    
    @Mappings({
        @Mapping(target = "ratings", ignore = true),
        @Mapping(target = "authors", ignore = true),
        @Mapping(target = "description", ignore = true),
        @Mapping(target = "assessment", ignore = true)
    })
    public Course mapToCourse(CourseOfAuthorDTO courseOfAuthorDTO);

    @Mapping(
        target = "averageRating",
        ignore = true
    )
    public CourseOfAuthorDTO mapToDto(Course course);
}
