package com.se.courses.repository;

import com.se.courses.entity.Experiment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExperimentRespository extends JpaRepository<Experiment, Long>,
        CustomizedRepoistory<Experiment, Long> {
    /**
     * 指定学生指定课程的全部实验
     * @param sId
     * @param cid
     * @return
     */
    @Query("SELECT e FROM Experiment e LEFT JOIN CourseDetail cd " +
            "ON e.course.id=cd.course.id WHERE cd.student.id=:sid AND cd.course.id=:cid")
    List<Experiment> list(@Param("sid") long sId, @Param("cid") long cid);

    /**
     * 指定教师指定课程的全部实验
     * @param tid
     * @param cid
     * @return
     */
    @Query("SELECT e FROM Experiment e WHERE e.course.teacher.id=:tid AND e.course.id=:cid")
    List<Experiment> listByTeacherId(@Param("tid") long tid, @Param("cid") long cid);

    @Query("SELECT COUNT(e) FROM Experiment e LEFT JOIN CourseDetail cd ON e.course.id=cd.course" +
            ".id WHERE e.course.id=:cid AND cd.student.id=:sid")
    int count(@Param("cid") long cid, @Param("sid") long sid);
    @Query("SELECT COUNT(e) FROM Experiment e WHERE e.course.id=:cid AND e.course.teacher.id=:tid")
    int countByTeacherId(@Param("cid") long cid, @Param("tid") long tid);
}
