package com.se.courses.repository;

import com.se.courses.entity.ExperimentDetail;
import com.se.courses.entity.HomeworkDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HomeworkDetailRepository extends JpaRepository<HomeworkDetail, Long> {

}
