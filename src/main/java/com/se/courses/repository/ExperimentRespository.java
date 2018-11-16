package com.se.courses.repository;

import com.se.courses.entity.Experiment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExperimentRespository extends JpaRepository<Experiment, Long> {
}
