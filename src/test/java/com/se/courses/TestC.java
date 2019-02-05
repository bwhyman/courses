package com.se.courses;

import com.se.courses.entity.U;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Optional;
@Slf4j
public class TestC {
    @Test
    public void test() {
        U user = new U();
        user.setName("asdf");
        Optional.ofNullable(user)
                .or(() -> {
                    U u = new U();
                    u.setName("dd");
                    return Optional.of(u);})
                .ifPresent(u -> log.debug(u.getName()));

    }

}
