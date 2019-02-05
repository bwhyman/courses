package com.se.courses.repository;

import com.se.courses.entity.Homework;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HomeworkReposityory extends JpaRepository<Homework, Long>,
        CustomizedRepoistory<Homework, Long> {

    @Query("SELECT h FROM Homework h " +
            "LEFT JOIN CourseDetail cd ON cd.course.id=h.course.id " +
            "WHERE cd.student.id=:sid AND cd.course.id=:cid")
    List<Homework> list(@Param("cid") long cid,@Param("sid") long sid);

    @Query("SELECT h FROM Homework h WHERE h.course.id=:cid AND h.course.teacher.id=:tid")
    List<Homework> listByTeacherId(@Param("cid") long cid, @Param("tid") long tid);



    @Query("SELECT COUNT(h) FROM Homework h LEFT JOIN CourseDetail cd on h.course.id=cd.course.id" +
            " WHERE h.course.id=:cid AND cd.student.id=:sid")
    int count(@Param("cid") long cid, @Param("sid") long sid);

    @Query("SELECT COUNT(h) FROM Homework h WHERE h.course.id=:cid AND h.course.teacher.id=:tid")
    int countByTeacherId(@Param("cid") long cid, @Param("tid") long tid);
}
