package com.sumerge.courses;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CoursesApplication {

	static CourseRecommender courseRecommender;

	CoursesApplication(@Qualifier("views") CourseRecommender courseRecommender)
	{
		CoursesApplication.courseRecommender = courseRecommender;
	} 

	public static void main(String[] args) {
		
		SpringApplication.run(CoursesApplication.class, args);
		System.out.println(courseRecommender.recommend());

	}

}
