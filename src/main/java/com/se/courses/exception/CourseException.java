package com.se.courses.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
public class CourseException extends ResponseStatusException {
    public CourseException(String reason) {
        super(HttpStatus.INTERNAL_SERVER_ERROR, reason);
    }
}
