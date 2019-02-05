package com.se.courses.repository;

import com.se.courses.entity.Experiment;
import com.se.courses.entity.ExperimentDetail;
import com.se.courses.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExperimentDetailRespostory extends JpaRepository<ExperimentDetail, Long>,
        CustomizedRepoistory<ExperimentDetail, Long> {
    /**
     * 查询指定实验未提交学生
     *
     * @return
     */
    /*@Query("SELECT cd.student.name FROM CourseDetail cd " +
            "WHERE cd.course.id=:cid AND cd.student.id " +
            "NOT IN (SELECT ed.student.id FROM ExperimentDetail ed WHERE ed.experiment.id=:expid) ")*/
    @Query("SELECT cd.student.name FROM CourseDetail cd " +
            "WHERE cd.course.id=:cid AND NOT EXISTS " +
            "(SELECT ed FROM ExperimentDetail ed " +
            "WHERE ed.experiment.id=:expid AND cd.student.id=ed.student.id)")
    List<String> listStudents(@Param("cid") long cid, @Param("expid") long expId);


    @Query("FROM ExperimentDetail ed WHERE ed.student.id=:sid AND ed.experiment.id=:expid")
    ExperimentDetail find(@Param("sid") long sid, @Param("expid") long expid);

    @Query("FROM ExperimentDetail ed WHERE ed.student.id=:sid AND ed.id=:edid")
    ExperimentDetail findBy(@Param("sid") long sid, @Param("edid") long edid);

    @Query("FROM ExperimentDetail ed WHERE ed.student.id=:sid AND ed.experiment.course.id=:cid")
    List<ExperimentDetail> list(@Param("cid") long cid, @Param("sid") long sid);
}
