package com.se.courses.repository;

import com.se.courses.entity.Student;
import lombok.extern.java.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>,
        CustomizedRepoistory<Student, Long> {
    /**
     * 查询指定学号的学生
     *
     * @param number
     * @return
     */
    @Query("FROM Student s WHERE s.number=:num")
    Student find(@Param("num") String number);

    @Query("SELECT COUNT(s) FROM Student s LEFT JOIN CourseDetail cd ON s.id=cd.student.id " +
            "WHERE cd.course.id=:cid AND cd.course.teacher.id=:tid")
    int count(@Param("cid") long cid, @Param("tid") long tid);

}
