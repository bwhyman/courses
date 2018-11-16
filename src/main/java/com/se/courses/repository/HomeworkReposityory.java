package com.se.courses.repository;

import com.se.courses.entity.Homework;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HomeworkReposityory extends JpaRepository<Homework, Long> {
}
