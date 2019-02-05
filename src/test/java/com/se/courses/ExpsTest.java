package com.se.courses;

import com.se.courses.entity.Experiment;
import com.se.courses.repository.ExperimentDetailRespostory;
import com.se.courses.repository.ExperimentRespository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ExpsTest {
    @Autowired
    private ExperimentRespository respository;
    @Autowired
    private ExperimentDetailRespostory edResp;
    @Test
    public void test() {
        var student = edResp.listStudents(2, 5);
        log.debug("" + student.size());
        for (String s : student) {
            log.debug(s);
        }
    }

}
