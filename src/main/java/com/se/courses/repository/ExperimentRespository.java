package com.se.courses.repository;

import com.se.courses.entity.Experiment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExperimentRespository extends JpaRepository<Experiment, Long> {
    /**
     * 指定学生的全部课程的全部实验
     * @param sId
     * @return
     */
    @Query("SELECT e FROM Experiment e LEFT JOIN CourseDetail cd " +
            "ON e.course.id=cd.course.id WHERE cd.student.id=:sid")
    List<Experiment> list(@Param("sid") long sId);
}
