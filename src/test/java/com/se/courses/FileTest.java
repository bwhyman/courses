package com.se.courses;


import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.springframework.util.FileSystemUtils;

import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Slf4j
public class FileTest {
    @Test
    public void test() throws IOException {
        /*Files.walk(Paths.get("G:/01/"), FileVisitOption.FOLLOW_LINKS)
                .sorted(Comparator.reverseOrder())
                .forEach(path -> {
                    try {
                        Files.deleteIfExists(path);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });*/
        //FileSystemUtils.deleteRecursively(Paths.get("G:/01/05"));

    }
}
