package com.sumerge.courses.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.sumerge.courses.dto.courses.GetCourseDTO;
import com.sumerge.courses.exceptions.AuthorNotFoundException;
import com.sumerge.courses.exceptions.CourseNotFoundException;
import com.sumerge.courses.exceptions.NoAuthorsForCourseException;
import com.sumerge.courses.exceptions.NotAuthorOfCourseException;
import com.sumerge.courses.mappers.courses.GetCourseDTOMapper;
import com.sumerge.courses.models.Assessment;
import com.sumerge.courses.models.Author;
import com.sumerge.courses.models.Course;
import com.sumerge.courses.models.Rating;
import com.sumerge.courses.recommenders.CourseRecommender;
import com.sumerge.courses.repositories.AssessmentRepository;
import com.sumerge.courses.repositories.AuthorRepository;
import com.sumerge.courses.repositories.CourseRepository;
import com.sumerge.courses.repositories.RatingRepository;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Service
@AllArgsConstructor
public class CourseService {

    CourseRepository courseRepository;

    AssessmentRepository assessmentRepository;

    AuthorRepository authorRepository;

    RatingRepository ratingRepository;

    AuthorService authorService;

    RatingService ratingService;

    @Getter
    CourseRecommender courseRecommender;

    GetCourseDTOMapper getCourseDTOMapper;

    public Course get(Integer id) throws CourseNotFoundException
    {
        Optional<Course> course = courseRepository.findById(id);
        if(course.isPresent())
            return course.get();
        throw new CourseNotFoundException("course with id " + id + " is not found");
    }

    public Course addCourse(Course c, Set<Integer> authorIds) throws NoAuthorsForCourseException, AuthorNotFoundException
    {
        if(authorIds.isEmpty())
            throw new NoAuthorsForCourseException("at least one author is required to create course");
        Set<Author> courseAuthors = authorService.getSetOfAuthors(authorIds);
        c.setAuthors(courseAuthors);
        Course saved = courseRepository.save(c);
        Assessment coursAssessment = c.getAssessment();
        coursAssessment.setCourse(saved);
        assessmentRepository.save(coursAssessment);
        authorService.addCourseToAuthors(courseAuthors, saved);
        return saved; 
    }

    public void UpdateCourseDescription(Integer id, String description) throws CourseNotFoundException, NotAuthorOfCourseException
    {
        Course course = get(id);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object userPrincipal = authentication.getPrincipal();
        String authorName = userPrincipal instanceof UserDetails ? ((UserDetails)userPrincipal).getUsername() : null;
        System.out.println(authorName);
        isAuthoredCourse(course, authorName);
        course.setDescription(description);
        courseRepository.save(course);
    }

    public void deleteCourse(Integer id) throws CourseNotFoundException
    {
        get(id);
        courseRepository.deleteById(id);
    }

    public GetCourseDTO viewCourse(Integer courseId) throws CourseNotFoundException
    {
        Course course = get(courseId);
        GetCourseDTO getCourseDTO = getCourseDTOMapper.mapToDto(course);
        getCourseDTO.setAverageRating(ratingService.getAverageRating(course.getRatings()));
        return getCourseDTO;
    }

    public List<GetCourseDTO> viewAllCourses(Pageable page)
    {
        Page<Course> courses = courseRepository.findAll(page);
        List<GetCourseDTO> getCourseDTOs = new ArrayList<>();
        courses.forEach((c) -> {
            GetCourseDTO getCourseDTO = getCourseDTOMapper.mapToDto(c);
            getCourseDTOs.add(getCourseDTO);
            getCourseDTO.setAverageRating(ratingService.getAverageRating(c.getRatings()));
        });
        return getCourseDTOs;
    }

    public void rateCourse(Integer courseId, Rating rating) throws CourseNotFoundException
    {
        Course course = get(courseId);
        course.getRatings().add(rating);
        rating.setCourse(course);
        courseRepository.save(course);
        ratingRepository.save(rating);
    }

    public boolean isAuthoredCourse(Course course, String authorUserName) throws NotAuthorOfCourseException
    {
        for(Author author : course.getAuthors())
            if(author.getName().equals(authorUserName))
                return true;
        throw new NotAuthorOfCourseException(authorUserName + " is not author of course " + course.getId());
    }

}
