package com.sumerge.courses;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.sumerge.courses.models.Course;


@SpringBootApplication
public class CoursesApplication {

	static CourseService courseService;

	@Autowired
	CoursesApplication(CourseService courseService)
	{
		CoursesApplication.courseService = courseService;
	}

	public static void main(String[] args) {
		
		SpringApplication.run(CoursesApplication.class, args);
		Course c = courseService.viewCourse("hussein");
		// courseService.addCourse(new Course(UUID.randomUUID().toString(), "math3", "sa3p moot", 2));
	}

}
