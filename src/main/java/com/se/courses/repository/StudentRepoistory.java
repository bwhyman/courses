package com.se.courses.repository;

import com.se.courses.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepoistory extends JpaRepository<Student, Long> {
}
