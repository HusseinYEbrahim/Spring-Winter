package com.sumerge.courses;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CoursesApplication {

	static CourseRecommender courseRecommender;

	CoursesApplication(CourseRecommender courseRecommender)
	{
		CoursesApplication.courseRecommender = courseRecommender;
	} 

	public static void main(String[] args) {
		/*
		 * This is 1st appraoch for wiring the dependency 
		 * 	by getting the Bean from the context and the Bean is configured in a config file
		 *  and since we have two beans returning CourseRecommender then we have to choose them in getBean by name
		 */
		SpringApplication.run(CoursesApplication.class, args);
		System.out.println(courseRecommender.recommend());

	}

}
