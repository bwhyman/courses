package com.se.courses.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Data
public class UnauthorizedException extends ResponseStatusException {
    public static final String COOKIE_PARES_ERROR = "Cookie解析错误，请重新登录";
    public static final String LOGIN_ERROR = "用户名密码错误";
    private static final String REASON = "授权错误";
    public UnauthorizedException() {
        super(HttpStatus.UNAUTHORIZED, REASON);
    }

    public UnauthorizedException(String reason) {
        super(HttpStatus.UNAUTHORIZED, reason);
    }
}
