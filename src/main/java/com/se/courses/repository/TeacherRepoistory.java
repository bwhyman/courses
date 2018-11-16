package com.se.courses.repository;

import com.se.courses.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepoistory extends JpaRepository<Teacher, Long> {
}
