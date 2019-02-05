package com.se.courses.interceptor;

import com.se.courses.exception.UnauthorizedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Slf4j
public class AdminInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object object) throws Exception {
        // TODO Auto-generated method stub
        long aId = (long) request.getAttribute("aId");
        if (aId != 1) {
            throw  new UnauthorizedException();
        }
        return true;
    }

}
