package com.se.courses.repository;

import com.se.courses.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long>,
        CustomizedRepoistory<Course, Long> {

    @Query("SELECT c FROM Course c WHERE c.id=:cid AND c.teacher.id=:tid")
    Course find(@Param("cid") long cid, @Param("tid") long tid);

    @Query("FROM Course c WHERE c.teacher.id=:tid")
    List<Course> list(@Param("tid") long tid);

}
