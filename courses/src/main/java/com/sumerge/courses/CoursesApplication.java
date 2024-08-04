package com.sumerge.courses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


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
	}

}
