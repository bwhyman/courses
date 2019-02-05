package com.se.courses.interceptor;

import com.se.courses.component.Constant;
import com.se.courses.component.EncryptorComponent;
import com.se.courses.entity.User;
import com.se.courses.exception.UnauthorizedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Optional;

@Component
@Slf4j
public class UserInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private EncryptorComponent encryptorComponent;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) {
        return Optional.ofNullable(request.getHeader(Constant.AUTHORIZATION))
                .or(() -> {
                    //log.debug("UserInterceptor UnauthorizedException");
                    throw new UnauthorizedException();
                })
                .map(j -> {
                    //log.debug("UserInterceptor map");
                    var map = encryptorComponent.decrypt(j);
                    // 默认反序列化为intege
                    request.setAttribute("uId", Long.valueOf(map.get("uId").toString()));
                    request.setAttribute("aId", Long.valueOf(map.get("aId").toString()));
                    return true;
                })
                .orElse(false);
    }
}
