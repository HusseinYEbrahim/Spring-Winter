package com.sumerge.courses.mappers.courses;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.sumerge.courses.dto.courses.PostCourseDTO;
import com.sumerge.courses.models.Course;

@Mapper(componentModel = "spring")
public interface PostCourseDTOMapper {

    @Mapping(target = "authorIds", ignore = true)
    PostCourseDTO mapToDto(Course course);

    @Mappings({
        @Mapping(
            target = "id",
            ignore = true
        ),
        @Mapping(
            target = "ratings",
            ignore = true
        ),
        @Mapping(
            target = "authors",
            ignore = true
        )}
    )
    Course mapToCourse(PostCourseDTO postCourseDTO);
    
}
