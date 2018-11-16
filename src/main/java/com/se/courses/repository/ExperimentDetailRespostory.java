package com.se.courses.repository;

import com.se.courses.entity.ExperimentDetail;
import com.se.courses.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExperimentDetailRespostory extends JpaRepository<ExperimentDetail, Long> {
    /**
     * 查询指定实验未提交学生
     * @return
     */
    @Query("SELECT cd.student " +
            "FROM CourseDetail cd " +
            "LEFT JOIN ExperimentDetail ed on cd.student.id=ed.student.id " +
            "WHERE ed.experiment.id != :expid or ed.experiment.id is null and cd.course.id=:cid GROUP BY cd.id")
    List<Student> listStudents(@Param("cid") long cid, @Param("expid") long expId);
}
