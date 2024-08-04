package main.java.sumerge;

import org.springframework.stereotype.Component;

@Component
public class HusseinRecommender implements CourseRecommender {

    @Override
    public String recommend() {
        return "Hussein's Recommender";
    }

    
    
}
