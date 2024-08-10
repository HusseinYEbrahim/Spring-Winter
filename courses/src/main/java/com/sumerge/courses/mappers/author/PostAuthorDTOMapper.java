package com.sumerge.courses.mappers.author;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.sumerge.courses.dto.author.PostAuthorDTO;
import com.sumerge.courses.models.Author;

@Mapper(componentModel = "spring")
public interface PostAuthorDTOMapper {
    
    @Mappings({
        @Mapping(target = "courses", ignore = true),
        @Mapping(target = "id", ignore = true)
    })
    public Author mapToAuthor(PostAuthorDTO postAuthorDTO);

    public PostAuthorDTO mapToDto(Author author);
    
}
