package com.sumerge.courses.mappers.author;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.sumerge.courses.dto.author.AuthorOfCourseDTO;
import com.sumerge.courses.models.Author;

@Mapper(componentModel = "spring")
public interface AuthorOfCourseDTOMapper {

    AuthorOfCourseDTO mapToDto(Author a);
    
    @Mappings({
        @Mapping(
            target = "birthDate",
            ignore = true
        ),
        @Mapping(
            target = "courses",
            ignore = true
        )
    })
    Author mapToAuthor(AuthorOfCourseDTO authorOfCourseDTO);
    
} 
