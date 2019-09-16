package com.se.courses.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class ExceptionController {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map getMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        String msg = "";
        for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
            msg = msg + fieldError.getDefaultMessage() + ";";
        }
        return Map.of("message", msg);
    }
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map getMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e,
                                                      HttpServletRequest request) {
        String msg = request.getRequestURI().replace("/api/", "/");
        return Map.of("message", "请求地址错误：" + msg);
    }

}
