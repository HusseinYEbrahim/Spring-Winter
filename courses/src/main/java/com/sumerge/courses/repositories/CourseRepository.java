package com.sumerge.courses.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sumerge.courses.models.Course;

@Repository
public class CourseRepository {
    @Autowired JdbcTemplate jdbcTemplate;

    public int save(Course c)
    {
        String sql = "INSERT INTO course (id, name, description, credit) VALUES (?, ?, ?, ?);";
        return jdbcTemplate.update(sql, c.getId(), c.getName(), c.getDescription(), c.getCredit());
    }

    public Course getById(String id)
    {
        Course course = null;
        try{
            String sql = "SELECT * FROM course WHERE id = ?;";
            course = jdbcTemplate.queryForObject(sql, 
                (rs, rowNum) -> new Course(rs.getObject("id", String.class), 
                    rs.getObject("name", String.class), 
                    rs.getObject("description", String.class), 
                    rs.getObject("credit", Integer.class)),
            id);
        }
        catch(Exception e)
        {
            // Most probably the course with this id is not there
            e.printStackTrace();
        }
        return course;
    }

    public int updateDescription(String courseId, String description)
    {
        String sql = "update course set description = '?' where id = '?';";
        return jdbcTemplate.update(sql, description, courseId);
    }

    public int deleteCourse(String courseId)
    {
        String sql = "delete from course where id = '?';";
        return jdbcTemplate.update(sql, courseId);
    }

    public List<Course> getAll()
    {
        String sql = "select * from course;";
        ArrayList<Map<String, Object>> rows =  (ArrayList<Map<String, Object>>) jdbcTemplate.queryForList(sql);
        ArrayList<Course> result = new ArrayList<>();
        for(Map<String, Object> row : rows)
        {
            Course c = new Course((String)row.get("id"), (String)row.get("name"), (String)row.get("description"), (Integer)row.get("credit"));
            result.add(c);
        }
        return result;
    }

    /*
     * It gets the top 3 rated courses by their avg rating, because a course can have multiple ratings
     */
    public List<Course> getBestThreeRatings()
    {
        String sql = "select C.* "+
                    "from course C inner join rating R "+ 
                        "on C.id = R.course_id "+
                    "group by C.id "+
                    "order by AVG(R.rating) DESC "+
                    "limit 3;";
        ArrayList<Map<String, Object>> rows =  (ArrayList<Map<String, Object>>) jdbcTemplate.queryForList(sql);
        ArrayList<Course> result = new ArrayList<>();
        for(Map<String, Object> row : rows)
        {
            Course c = new Course((String)row.get("id"), (String)row.get("name"), (String)row.get("description"), (Integer)row.get("credit"));
            result.add(c);
        }
        return result;
    }
}
