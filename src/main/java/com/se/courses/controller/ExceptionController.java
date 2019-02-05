package com.se.courses.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
@Slf4j
public class ExceptionController {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus
    public Map get(MethodArgumentNotValidException e) {
        String msg = "";
        for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
            msg = msg + fieldError.getDefaultMessage() + ";";
        }
        return Map.of("message", msg);
    }
}
