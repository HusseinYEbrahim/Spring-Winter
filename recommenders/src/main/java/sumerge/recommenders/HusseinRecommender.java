package sumerge.recommenders;


import org.springframework.stereotype.Component;

import com.sumerge.courses.CourseRecommender;

@Component
public class HusseinRecommender implements CourseRecommender  {

    @Override
    public String recommend() {
        return "Hussein's Recommender";
    }

    
    
}
