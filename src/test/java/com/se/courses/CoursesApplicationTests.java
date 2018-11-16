package com.se.courses;

import com.se.courses.entity.Course;
import com.se.courses.entity.CourseDetail;
import com.se.courses.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class CoursesApplicationTests {

    @Test
    public void contextLoads() {
        log.debug("debug");
        log.info("中文");
        Course c = new Course(10);
        log.info(c.toString());

    }

}
