package com.se.courses.component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.se.courses.exception.UnauthorizedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component
@Slf4j
public class ObjectMapperComponent {
    @Autowired
    private ObjectMapper mapper;
    public String writeValueAsString(Map payload) {
        try {
            return mapper.writeValueAsString(payload);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Map<String, Object> readValue(String json) {
        try {
            return mapper.readValue(json, Map.class);
        } catch (IOException e) {
            throw new UnauthorizedException();
        }
    }
}
