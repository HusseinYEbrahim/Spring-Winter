package com.sumerge.courses.controllers;

import java.util.List;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sumerge.courses.dto.courses.GetCourseDTO;
import com.sumerge.courses.dto.courses.PostCourseDTO;
import com.sumerge.courses.dto.rating.RatingDTO;
import com.sumerge.courses.exceptions.AuthorNotFoundException;
import com.sumerge.courses.exceptions.CourseNotFoundException;
import com.sumerge.courses.exceptions.NoAuthorsForCourseException;
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
    public ResponseEntity<String> add(@RequestBody PostCourseDTO courseDTO)
    {
        Course course = postCourseDTOMapper.mapToCourse(courseDTO);
        Set<Integer> authorIds = courseDTO.getAuthorIds();
        try {
            Course saved = courseService.addCourse(course, authorIds);
            return ResponseEntity.ok().body("course is added successfully with id " + saved.getId());
        } catch (AuthorNotFoundException e) {
            return ResponseEntity.badRequest().body("error occured : " + e.getMessage());
        } catch (NoAuthorsForCourseException e) {
            return ResponseEntity.badRequest().body("error occurred : " + e.getMessage());
        } 
    }

    @GetMapping("/view/{id}")
    public ResponseEntity<Object> view(@PathVariable Integer id)
    {
        try {
            GetCourseDTO getCourseDTO = courseService.viewCourse(id);
            return ResponseEntity.ok().body(getCourseDTO);
        } catch (CourseNotFoundException e) {
            return ResponseEntity.badRequest().body("error occured : " + e.getMessage()); 
        }
    }

    @PutMapping("/update/description/{id}")
    public ResponseEntity<String> updateCourseDescription(@PathVariable Integer id, @RequestBody String description)
    {
        try {
            courseService.UpdateCourseDescription(id, description);
            return ResponseEntity.ok().body("course " + id +" has been updated successfully");
        } catch (CourseNotFoundException e) {
            return ResponseEntity.badRequest().body("error occured : " + e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable Integer id)
    {
        try {
            courseService.deleteCourse(id);
            return ResponseEntity.ok().body(null);
        } catch (CourseNotFoundException e) {
            return ResponseEntity.badRequest().body("error occurred : " + e.getMessage());
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<GetCourseDTO>> viewAll(Pageable page)
    {
        System.out.println(page.getPageSize() + " " + page.getPageNumber());
        return ResponseEntity.ok().body(courseService.viewAllCourses(page));
    }

    @GetMapping("/discover")
    public ResponseEntity<List<GetCourseDTO>> getRecommendations()
    {
        return ResponseEntity.ok().body(courseService.getCourseRecommender().recommend());
    }

    @PostMapping("/rate/{id}")
    public ResponseEntity<Object> rateCourse(@RequestParam Integer id, @RequestBody RatingDTO getRatingDTO)
    {
        Rating rating = getRatingDTOMapper.mapToRating(getRatingDTO);
        try {
            courseService.rateCourse(id, rating);
            return ResponseEntity.ok().body(null);
        } catch (CourseNotFoundException e) {
            return ResponseEntity.badRequest().body("error occured : " + e.getMessage());
        }
    }
}
