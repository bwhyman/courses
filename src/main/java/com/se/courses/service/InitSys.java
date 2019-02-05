package com.se.courses.service;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InitSys implements InitializingBean {
    @Autowired
    private InitService initService;

    @Override
    public void afterPropertiesSet() {
        // TODO Auto-generated method stub
        initService.initAuthority();
        initService.initTeacher();
    }
}
