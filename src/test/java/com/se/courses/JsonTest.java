package com.se.courses;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.se.courses.component.EncryptorComponent;
import com.se.courses.component.ObjectMapperComponent;
import com.se.courses.entity.A;
import com.se.courses.entity.U;
import com.se.courses.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;


@Slf4j
public class JsonTest {

    @Autowired
    private EncryptorComponent encryptorComponent;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ObjectMapperComponent oComponent;

    @Test
    public void test() throws JsonProcessingException {
        U u = new U();
        u.setName("BO");
        A a = new A();
        a.setName("925");
        a.setU(u);
        u.setAs(Set.of(a));

        ObjectMapper objectMapper = new ObjectMapper();
        String str = objectMapper.writeValueAsString(a);
        log.debug(str);
    }

}
