package com.sumerge.courses;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class CoursesApplication {

	public static void main(String[] args) {
		CourseRecommender courseRecommender;
		/*
		 * This is 1st appraoch for wiring the dependency 
		 * 	by getting the Bean from the context and the Bean is configured in a config file
		 *  and since we have two beans returning CourseRecommender then we have to choose them in getBean by name
		 */
		ApplicationContext ctx = SpringApplication.run(CoursesApplication.class, args);
		courseRecommender = ctx.getBean("ratingRecommender", CourseRecommender.class);
		System.out.println(courseRecommender.recommend());

	}

}
