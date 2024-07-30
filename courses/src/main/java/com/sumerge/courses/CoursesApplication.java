package com.sumerge.courses;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.sumerge.courses.recommenders.ViewsRecommender;

@SpringBootApplication
public class CoursesApplication {

	public static void main(String[] args) {
		
		ApplicationContext ctx = SpringApplication.run(CoursesApplication.class, args);
		CourseService cs = new CourseService();
		cs.setCourseRecommender(ctx.getBean(ViewsRecommender.class));
		System.out.println(cs.courseRecommender.recommend());

	}

}
