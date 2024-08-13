package com.sumerge.courses.controllers;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sumerge.courses.dto.courses.GetCourseDTO;
import com.sumerge.courses.dto.courses.PostCourseDTO;
import com.sumerge.courses.dto.rating.RatingDTO;
import com.sumerge.courses.exceptions.AuthorNotFoundException;
import com.sumerge.courses.exceptions.CourseNotFoundException;
import com.sumerge.courses.exceptions.NoAuthorsForCourseException;
import com.sumerge.courses.exceptions.NotAuthorOfCourseException;
import com.sumerge.courses.mappers.courses.GetCourseDTOMapper;
import com.sumerge.courses.mappers.courses.PostCourseDTOMapper;
import com.sumerge.courses.mappers.rating.GetRatingDTOMapper;
import com.sumerge.courses.models.Course;
import com.sumerge.courses.models.Rating;
import com.sumerge.courses.services.CourseService;

@RestController
@RequestMapping("/courses")
public class CourseController {
    
    @Autowired
    CourseService courseService;

    @Autowired
    PostCourseDTOMapper postCourseDTOMapper;

    @Autowired
    GetCourseDTOMapper getCourseDTOMapper;

    @Autowired 
    GetRatingDTOMapper getRatingDTOMapper;

    @PostMapping("/add")
    public ResponseEntity<Object> add(@RequestBody PostCourseDTO courseDTO) throws NoAuthorsForCourseException, AuthorNotFoundException
    {
        Course course = postCourseDTOMapper.mapToCourse(courseDTO);
        Set<Integer> authorIds = courseDTO.getAuthorIds();
        Course saved = courseService.addCourse(course, authorIds);
        return ResponseEntity.status(201).body("course is added successfully with id " + saved.getId());
    }

    @GetMapping("/view/{id}")
    public ResponseEntity<Object> view(@PathVariable Integer id) throws CourseNotFoundException
    {
        GetCourseDTO getCourseDTO = courseService.viewCourse(id);
        return ResponseEntity.ok().body(getCourseDTO);
    }

    @PutMapping("/update/description/{id}")
    public ResponseEntity<Object> updateCourseDescription(@PathVariable Integer id, @RequestBody String description) throws CourseNotFoundException, NotAuthorOfCourseException
    {
        courseService.UpdateCourseDescription(id, description);
        return ResponseEntity.ok().body("course " + id +" has been updated successfully");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable Integer id) throws CourseNotFoundException
    {
        courseService.deleteCourse(id);
        return ResponseEntity.ok().body(null);
    }

    @GetMapping("/all")
    public ResponseEntity<Object> viewAll(Pageable page)
    {
        System.out.println(page.getPageSize() + " " + page.getPageNumber());
        return ResponseEntity.ok().body(courseService.viewAllCourses(page));
    }

    @GetMapping("/discover")
    public ResponseEntity<Object> getRecommendations()
    {
        return ResponseEntity.ok().body(courseService.getCourseRecommender().recommend());
    }

    @PostMapping("/rate/{id}")
    public ResponseEntity<Object> rateCourse(@PathVariable Integer id, @RequestBody RatingDTO getRatingDTO) throws CourseNotFoundException
    {
        Rating rating = getRatingDTOMapper.mapToRating(getRatingDTO);
        courseService.rateCourse(id, rating);
        return ResponseEntity.ok().body(null);
    }
}
