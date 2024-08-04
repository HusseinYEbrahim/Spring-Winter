package com.sumerge.courses.configurations;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.sumerge.courses.CourseRecommender;
import com.sumerge.courses.CourseService;
import com.sumerge.courses.recommenders.RatingRecommender;
import com.sumerge.courses.recommenders.ViewsRecommender;
import com.sumerge.courses.repositories.CourseRepository;

import sumerge.recommenders.HusseinRecommender;

@Configuration
@Import({HusseinRecommender.class})
public class AppConfig {

    @Value("${spring.datasource.url}")
    private String dbUrl;

    @Value("${spring.datasource.username}")
    private String dbUsername;

    @Value("${spring.datasource.password}")
    private String dbPassword;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl(dbUrl); 
        dataSource.setUsername(dbUsername);
        dataSource.setPassword(dbPassword); 
        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public CourseRecommender ratingRecommender()
    {
        return new RatingRecommender();
    }

    @Bean
    public CourseRecommender viewsRecommender()
    {
        return new ViewsRecommender();
    }

    @Bean 
    public CourseRecommender husseinRecommender()
    {
        return new CourseRecommender() {
            public String recommend(){
                return "Overriden value !!";
            }
        };
    }

    @Bean 
    public CourseRepository getCourseRepository()
    {
        return new CourseRepository();
    }

    @Bean 
    public CourseService getCourseService(CourseRecommender husseinRecommender)
    {
        return new CourseService(husseinRecommender);
    }
}
