package com.sumerge.courses.mappers.rating;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.sumerge.courses.dto.rating.RatingDTO;
import com.sumerge.courses.models.Rating;

@Mapper(componentModel = "spring")
public interface GetRatingDTOMapper {
    
    RatingDTO mapToDto(Rating rating);

    @Mappings({
        @Mapping(
            target = "course",
            ignore = true
        ),
        @Mapping(
            target = "id",
            ignore = true
        )
    }
    )
    Rating mapToRating(RatingDTO ratingDTO);
}
