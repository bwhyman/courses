package com.se.courses;

import com.se.courses.entity.Student;
import com.se.courses.repository.ExperimentDetailRespostory;
import com.se.courses.service.CourseService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class BatchingStudentByExcelFileTest {
    @Autowired
    private CourseService courseService;
    @Autowired
    private ExperimentDetailRespostory respostory;

    @Test
    public void test() {
        log.debug("" + respostory.listStudents(6, 1).size());
    }
}
