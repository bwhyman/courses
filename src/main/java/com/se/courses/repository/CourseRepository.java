package com.se.courses.repository;

import com.se.courses.entity.Course;
import com.se.courses.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    /**
     * 指定课程的全部学生
     * @param cid
     * @return
     */
    @Query("SELECT cd.student FROM CourseDetail cd WHERE cd.course.id=:cid")
    List<Student> listStudents(@Param("cid") long cid);
}
