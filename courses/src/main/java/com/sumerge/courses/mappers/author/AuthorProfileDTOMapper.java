package com.sumerge.courses.mappers.author;

import org.mapstruct.Mapper;

import com.sumerge.courses.dto.author.AuthorProfileDTO;
import com.sumerge.courses.models.Author;

@Mapper(componentModel = "spring")
public interface AuthorProfileDTOMapper {
    
    AuthorProfileDTO mapToDto(Author a);

    Author mapToAuthor(AuthorProfileDTO authorProfileDTO);
}
