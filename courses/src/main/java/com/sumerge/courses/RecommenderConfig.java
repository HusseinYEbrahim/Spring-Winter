package com.sumerge.courses;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sumerge.courses.recommenders.RatingRecommender;
import com.sumerge.courses.recommenders.ViewsRecommender;

@Configuration
public class RecommenderConfig {

    @Bean
    public CourseRecommender ratingRecommender()
    {
        return new RatingRecommender();
    }

    @Bean
    public CourseRecommender viewsRecommender()
    {
        return new ViewsRecommender();
    }
    
}
