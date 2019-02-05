package com.se.courses;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.security.crypto.codec.Hex;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;
@Slf4j
public class Base64ToFileTest {
    @Test
    public void test() throws NoSuchAlgorithmException {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[20];
        random.nextBytes(salt);
        log.debug("" + salt.length);
        String  result = new String(Hex.encode(salt));
        log.debug(result);
        log.debug("" + result.length());


    }
}
