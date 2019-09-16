package com.se.courses.repository;

import com.se.courses.entity.Homework;
import com.se.courses.entity.HomeworkDetail;
import com.se.courses.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HomeworkDetailRepository extends CustomizedRepoistory<HomeworkDetail, Long> {
    /**
     * 指定学生的指定作业的详细信息
     * @param hId 作业ID
     * @param uId 学生ID
     * @return 返回作业详细信息
     */
    @Query("FROM HomeworkDetail h WHERE h.student.id=:uId AND h.homework.id=:hId")
    HomeworkDetail find(@Param("hId") long hId, @Param("uId") long uId);

    @Query("FROM HomeworkDetail hd WHERE hd.homework.course.id=:cid AND hd.student.id=:sid")
    List<HomeworkDetail> list(@Param("cid") long cid, @Param("sid") long sid);

    @Query("SELECT cd.student.name FROM CourseDetail cd " +
            "WHERE cd.course.id=:cid AND NOT EXISTS " +
            "(SELECT hd FROM HomeworkDetail hd " +
            "WHERE hd.homework.id=:hid AND cd.student.id=hd.student.id)")
    List<String> listStudents(@Param("cid") long cid, @Param("hid") long hid);


}
