package com.se.courses.repository;

import com.se.courses.entity.Homework;
import com.se.courses.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HomeworkReposityory extends JpaRepository<Homework, Long> {
}
