package com.sumerge.courses.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sumerge.courses.models.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {

    @Query(
        "select C " +
        "from Course C left outer join Rating R " + 
        "on C = R.course " +
        "group by C " +
        "order by avg(coalesce(R.rating, 0)) desc " + 
        "limit 3"
    )
    public List<Course> getBestThreeRatings();

}
