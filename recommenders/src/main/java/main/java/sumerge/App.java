package main.java.sumerge;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(HusseinRecommender.class);
        HusseinRecommender recommender = context.getBean(HusseinRecommender.class);
        System.out.println(recommender.recommend());
        context.close();	
    }
}
