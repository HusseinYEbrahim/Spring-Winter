package com.sumerge.courses.services;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sumerge.courses.models.Rating;
import com.sumerge.courses.repositories.RatingRepository;

@Service
public class RatingService {
    
    @Autowired
    RatingRepository ratingRepository;

    public double getAverageRating(Set<Rating> ratings)
    {
        if(ratings.isEmpty())
            return 0.0;
        double sum = 0;
        for(Rating rating : ratings)
            sum += rating.getRating();
        return sum / ratings.size();
    }
}
