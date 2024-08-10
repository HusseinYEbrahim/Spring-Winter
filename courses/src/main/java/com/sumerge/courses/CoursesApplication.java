package com.sumerge.courses;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.sumerge.courses.services.CourseService;


@SpringBootApplication
public class CoursesApplication {

	static CourseService courseService;

	CoursesApplication(CourseService courseService)
	{
		CoursesApplication.courseService = courseService;
	}

	public static void main(String[] args) {
		SpringApplication.run(CoursesApplication.class, args);
	}

}
