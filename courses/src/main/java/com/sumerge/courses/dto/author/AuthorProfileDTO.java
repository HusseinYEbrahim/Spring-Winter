package com.sumerge.courses.dto.author;

import java.util.Set;

import com.sumerge.courses.dto.courses.CourseOfAuthorDTO;

import lombok.Data;

@Data
public class AuthorProfileDTO {

    Integer id;

    String name;

    String email;

    Set<CourseOfAuthorDTO> courses;
    
}
