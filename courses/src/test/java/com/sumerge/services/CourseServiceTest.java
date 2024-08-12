package com.sumerge.services;

import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.sumerge.courses.dto.courses.GetCourseDTO;
import com.sumerge.courses.exceptions.AuthorNotFoundException;
import com.sumerge.courses.exceptions.CourseNotFoundException;
import com.sumerge.courses.exceptions.NoAuthorsForCourseException;
import com.sumerge.courses.mappers.courses.GetCourseDTOMapper;
import com.sumerge.courses.models.Assessment;
import com.sumerge.courses.models.Author;
import com.sumerge.courses.models.Course;
import com.sumerge.courses.recommenders.CourseRecommender;
import com.sumerge.courses.repositories.AssessmentRepository;
import com.sumerge.courses.repositories.AuthorRepository;
import com.sumerge.courses.repositories.CourseRepository;
import com.sumerge.courses.repositories.RatingRepository;
import com.sumerge.courses.services.AuthorService;
import com.sumerge.courses.services.CourseService;
import com.sumerge.courses.services.RatingService;


@ExtendWith(MockitoExtension.class)
public class CourseServiceTest {
    
    @Mock
    AuthorRepository authorRepository;

    @Mock
    CourseRepository courseRepository;

    @Mock
    AssessmentRepository assessmentRepository;

    @Mock
    RatingRepository ratingRepository;

    @Mock
    AuthorService authorService;

    @Mock
    RatingService ratingService;

    @Mock 
    CourseRecommender courseRecommender;

    @Mock
    GetCourseDTOMapper getCourseDTOMapper;

    @InjectMocks
    CourseService courseService;

    public void mockGet()
    {
        Optional<Course> optionalCourse = Optional.of(
            Course.builder()
            .credit(2)
            .description("That is this course")
            .name("CSEN")
            .build()
        );

        when(courseRepository.findById(Mockito.any(Integer.class)))
            .thenReturn(optionalCourse);
    }


    public void mockMapper()
    {
        GetCourseDTO getCourseDTO = GetCourseDTO.builder()
                                    .credit(1)
                                    .description("What is that please ? ")
                                    .id(2)
                                    .build();
        when(getCourseDTOMapper.mapToDto(Mockito.any(Course.class)))
            .thenReturn(getCourseDTO);
    }

    @Test
    public void CourseService_get_ShouldReturnCourse()
    {
        mockGet();
        int id = 1;
        try {
            Course course = courseService.get(id);
            Assertions.assertThat(course).isNotNull();
        } catch (CourseNotFoundException e) {
            Assertions.assertThat(e).isNull();
            e.printStackTrace();
        }
    }

    @Test
    public void CourseService_get_ShouldThrowNotFoundException()
    {
        
        int id = 1;
        Optional<Course> optionalEmptyCourse = Optional.ofNullable(null);

        when(courseRepository.findById(Mockito.any(Integer.class)))
            .thenReturn(optionalEmptyCourse);

        try {
            Course course = courseService.get(id);
            Assertions.assertThat(course).isNotNull();
        } catch (CourseNotFoundException e) {
            Assertions.assertThat(e).isNotNull();
        }
    }


    @Test
    public void CourseService_AddCourse_Sucess_ShouldReturnAddedCourse() throws AuthorNotFoundException
    {
        Author author = Author.builder()
                        .email("negm@gmail.com")
                        .name("Mr Max Payne")
                        .build();

        Assessment courseAssessment = Assessment.builder()
                                    .content("Who is Mr Max Payne?")
                                    .build();
        Course course = Course.builder()        
                    .assessment(courseAssessment)
                    .credit(2)
                    .description("Best Course explaining who is Mr Max Payne")
                    .build();
        Set<Author> courseAuthors = new HashSet<>();
        courseAuthors.add(author);
        try {
            when(authorService.getSetOfAuthors(Mockito.anySet()))
                .thenReturn(courseAuthors);
        } catch (AuthorNotFoundException e) {
            e.printStackTrace();
        }
        when(courseRepository.save(Mockito.any(Course.class)))
            .thenReturn(Course.builder().build());
        Set<Integer> authorIds = new HashSet<>();
        authorIds.add(1);
        try {
            Course saved = courseService.addCourse(course, authorIds);
            Assertions.assertThat(saved).isNotNull();
        } catch (NoAuthorsForCourseException e) {
            e.printStackTrace();
        } catch (AuthorNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void CourseService_AddCourse_Failure_ShouldThrowNoAuthorsForCourseException()
    {

        Assessment courseAssessment = Assessment.builder()
                            .content("Who is Mr Max Payne?")
                            .build();
        Course course = Course.builder()        
            .assessment(courseAssessment)
            .credit(2)
            .description("Best Course explaining who is Mr Max Payne")
            .build();

        try {
            courseService.addCourse(course, new HashSet<>());
        } catch (NoAuthorsForCourseException e) {
            Assertions.assertThat(e).isNotNull();
            e.printStackTrace();
        } catch (AuthorNotFoundException e) {   
            e.printStackTrace();
        }
    }

    
    @Test
    public void CourseService_ViewCourse_ShouldReturnCourseDTO()
    {
        mockGet();
        mockMapper();
        try {
            GetCourseDTO getCourseDTO = courseService.viewCourse(1);
            Assertions.assertThat(getCourseDTO).isNotNull();
        } catch (CourseNotFoundException e) {
            e.printStackTrace();
        }
        
    }

    @Test
    public void CourseServece_viewAllCourses_ShouldReturnList()
    {
        mockMapper();
        Course course1 = Course.builder().credit(2).name("CSEN").build();
        Course course2 = Course.builder().credit(2).name("CSEN2").build();
        
        List<Course> courses = Arrays.asList(course1, course2);
        
        PageRequest pageable = PageRequest.of(0, 10);

        Page<Course> coursesPage = new PageImpl<>(courses, pageable, courses.size());

        when(courseRepository.findAll(Mockito.any(Pageable.class)))
            .thenReturn(coursesPage);
        
        List<GetCourseDTO> returnedCourses = courseService.viewAllCourses(PageRequest.of(0, 2));
        Assertions.assertThat(returnedCourses).isNotNull();
        Assertions.assertThat(returnedCourses.size()).isEqualTo(2);
    }

}
