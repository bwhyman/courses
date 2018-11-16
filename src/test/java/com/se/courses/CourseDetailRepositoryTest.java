package com.se.courses;

import com.se.courses.entity.Course;
import com.se.courses.entity.CourseDetail;
import com.se.courses.entity.Student;
import com.se.courses.repository.CourseDetailRepository;
import com.se.courses.repository.ExperimentDetailRespostory;
import com.se.courses.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class CourseDetailRepositoryTest {
    @Autowired
    private CourseDetailRepository cr;
    @Autowired
    private UserRepository ur;
    @Autowired
    private ExperimentDetailRespostory er;
    @Test
    public void contextLoads() {
        List<Student> students = er.listStudents(1, 2);
        for (Student student : students) {
            log.info(student.getName());
        }

    }

}
