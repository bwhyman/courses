package com.se.courses.repository;

import com.se.courses.entity.Course;
import com.se.courses.entity.CourseDetail;
import com.se.courses.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseDetailRepository extends JpaRepository<CourseDetail, Long>,
        CustomizedRepoistory<CourseDetail, Long> {

    @Query("SELECT cd.course FROM CourseDetail cd WHERE cd.student.id=:sid")
    List<Course> list(@Param("sid") long sid);

    @Query("SELECT cd.course FROM CourseDetail cd WHERE cd.course.id=:cid AND cd.student.id=:sid")
    Course find(@Param("cid") long cid, @Param("sid") long sid);

    @Query("SELECT cd.student FROM CourseDetail cd WHERE cd.course.id=:cid AND cd.course.teacher" +
            ".id=:tid")
    List<Student> listStudents(@Param("cid") long cid, @Param("tid") long tid);
}
