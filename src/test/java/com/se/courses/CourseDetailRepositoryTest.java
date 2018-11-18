package com.se.courses;

import com.se.courses.entity.Course;
import com.se.courses.entity.CourseDetail;
import com.se.courses.entity.Experiment;
import com.se.courses.entity.Student;
import com.se.courses.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class CourseDetailRepositoryTest {
    @Autowired
    private CourseDetailRepository cr;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private UserRepository ur;
    @Autowired
    private ExperimentDetailRespostory er;
    @Autowired
    private ExperimentRespository expR;

    @Test
    public void testCourseResp() {
        for (Student student : courseRepository.listStudents(2)) {
            log.info(student.getName());
        }
    }

    @Test
    public void testExperimentResp() {
        for (Experiment experiment : expR.list(3)) {
            log.info(experiment.getName());
        }
    }

    @Test
    public void testExperimentDetail() {
        Optional.ofNullable(er.find(1, 2))
                .ifPresent(experimentDetail ->
                        log.info(experimentDetail.getCompleteTime().toString())
                );
    }

    @Test
    public void contextLoads() {
        List<Student> students = er.listStudents(1, 2);
        for (Student student : students) {
            log.info(student.getName());
        }
    }

}
