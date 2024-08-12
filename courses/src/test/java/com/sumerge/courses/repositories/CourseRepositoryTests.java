package com.sumerge.courses.repositories;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import com.sumerge.courses.models.Assessment;
import com.sumerge.courses.models.Author;
import com.sumerge.courses.models.Course;
import com.sumerge.courses.models.Rating;

/*
 * @DataJpaTest : annotation that makes some configurations for using embedded database, using datasources and setting up components of data jpa
 */
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class CourseRepositoryTests {
    
    @Autowired
    CourseRepository courseRepository;

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    RatingRepository ratingRepository;

    @Autowired
    AssessmentRepository assessmentRepository;

    @Test
    public void CourseRepository_Save_ShouldReturnSaved()
    {
        Assessment courseAssessment = Assessment.builder()
                                        .content("What is SpringWinter?")
                                        .build();

        Course course = Course.builder()
                            .credit(2)
                            .description("Hello Baby Joy")
                            .name("CSEN1003")
                            .assessment(courseAssessment)
                            .authors(new HashSet<>())
                            .build();
        
        Author author = Author.builder()
                            .email("max-payne@gmail.com")
                            .name("Mr Max")
                            .courses(new HashSet<>())
                            .build();
        
        course.getAuthors().add(author);
        author.getCourses().add(course);
        courseAssessment.setCourse(course);

        Course savedCourse = courseRepository.save(course);
        Author savedAuthor = authorRepository.save(author);
        Assessment savedAssessment = assessmentRepository.save(courseAssessment);

        Assertions.assertThat(savedCourse).isNotNull();
        Assertions.assertThat(savedCourse.getAssessment()).isNotNull();
        Assertions.assertThat(savedAssessment.getCourse()).isNotNull();
        Assertions.assertThat(savedAuthor).isNotNull();
        Assertions.assertThat(savedAuthor.getCourses()).isNotNull();
        Assertions.assertThat(savedAuthor.getCourses().size()).isEqualTo(1);
    }

    @Test
    public void CourseRepository_FindBestThreeRatings_ShouldReturnListOfCourses()
    {
        // create 5 courses
        List<Course> savedCourses = new ArrayList<>();
        for(int i = 0; i < 5; ++i)
        {
            Set<Rating> ratings = new HashSet<>();
            Rating rating = Rating.builder()
                                .rating(i+1)
                                .build();
            ratings.add(rating);
            Course toBeSaved = Course.builder()
                                .credit(5)
                                .description("Math3")
                                .name("XYZ")
                                .ratings(ratings)
                                .build();
            rating.setCourse(toBeSaved);
            Course saved = courseRepository.save(toBeSaved);
            ratingRepository.save(rating);
            savedCourses.add(saved);
        }
        List<Course> bestThree = courseRepository.getBestThreeRatings();
        for(int i = 0; i < bestThree.size(); ++i)
        {
            Assertions.assertThat(bestThree.get(i)).isNotNull();
            Assertions.assertThat(bestThree.get(i).getId()).isEqualTo(savedCourses.get(5-i-1).getId());
        }
    }
}
