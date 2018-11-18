package com.se.courses.repository;

import com.se.courses.entity.ExperimentDetail;
import com.se.courses.entity.HomeworkDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HomeworkDetailRepository extends JpaRepository<HomeworkDetail, Long> {
    /**
     * 指定学的指定作业的详细信息
     * @param hId
     * @param uId
     * @return
     */
    @Query("FROM HomeworkDetail h WHERE h.student.id=:uId AND h.homework.id=:hId")
    HomeworkDetail find(@Param("hId") long hId, @Param("uId") long uId);
}
