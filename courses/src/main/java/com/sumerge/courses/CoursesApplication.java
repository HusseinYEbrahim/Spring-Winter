package com.sumerge.courses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.sumerge.courses.configurations.AppConfig;

public class CoursesApplication {

	static CourseService courseService;

	@Autowired
	CoursesApplication(CourseService courseService)
	{
		CoursesApplication.courseService = courseService;
	}

	public static void main(String[] args) {
		
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);	
		CourseService cs = context.getBean(CourseService.class);
		System.out.println(cs.courseRecommender.recommend());

		context.close();
	}

}
