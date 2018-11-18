package com.se.courses.repository;

import com.se.courses.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepoistory extends JpaRepository<Student, Long> {
    /**
     * 查询指定学号的学生
     * @param number
     * @return
     */
    @Query("FROM Student s WHERE s.number=:num")
    Student find(@Param("num") String number);


}
