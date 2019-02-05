package com.se.courses;

import com.se.courses.component.CommonComponent;
import com.se.courses.component.ObjectMapperComponent;
import com.se.courses.entity.Student;
import com.se.courses.repository.CourseRepository;
import com.se.courses.repository.ExperimentRespository;
import com.se.courses.service.CourseService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
@Transactional
public class CourseServiceTest {
    @Autowired
    private CourseService courseService;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private ObjectMapperComponent mapperComponent;
    @Autowired
    private ExperimentRespository experimentRespository;
    @Test
    public void listStudentCourses() {
//        for (Student student : courseService.listStudents(1)) {
//            log.debug(student.getName());
//        }

    }

    @Test
    public void listCourse() {
        int n = experimentRespository.count(1, 1);
        log.debug("" + n);
    }
}
