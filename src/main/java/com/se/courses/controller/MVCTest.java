package com.se.courses.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
public class MVCTest {
    @PostMapping("/files")
    public void postFiles(MultipartFile[] files) {
        log.debug("{}", files.length);
    }
}
