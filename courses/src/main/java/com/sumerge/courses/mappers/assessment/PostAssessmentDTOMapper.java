package com.sumerge.courses.mappers.assessment;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.sumerge.courses.dto.assessment.PostAssessmentDTO;
import com.sumerge.courses.models.Assessment;

@Mapper(componentModel = "spring")
public interface PostAssessmentDTOMapper {
    
    PostAssessmentDTO mapToDto(Assessment a);
    
    @Mappings({
        @Mapping(
            target = "id",
            ignore = true
        ),
        @Mapping(
            target = "course",
            ignore = true
        )
    })
    Assessment mapToAssessment(PostAssessmentDTO postAssessmentDTO);
}
