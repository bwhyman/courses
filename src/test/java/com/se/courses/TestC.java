package com.se.courses;

import com.se.courses.entity.U;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
@Slf4j
public class TestC {
    @Test
    public void test() {
        var deadLineTile = LocalDateTime.of(2019, 3, 9, 23, 45);
        var now = LocalDateTime.now();
        log.debug("" + now.isBefore(deadLineTile));

    }

    @Test
    public void test2() {
        String msg = "/api/courses/a/a";
        log.debug(msg.replace("/api/", "/"));
    }

}
