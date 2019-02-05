package com.se.courses.service;

import com.se.courses.entity.Authority;
import com.se.courses.entity.Teacher;
import com.se.courses.repository.AuthorityRepository;
import com.se.courses.repository.StudentRepository;
import com.se.courses.repository.TeacherRepoistory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
public class InitService {
    @Autowired
    private TeacherRepoistory teacherRep;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthorityRepository authorityRep;

    public void initTeacher() {
        if (teacherRep.count() == 0) {
            Teacher teacher = new Teacher();
            teacher.setName("王波");
            String number = "1007";
            teacher.setNumber(number);
            teacher.setAuthority(new Authority(Authority.TEACHER_ID));
            teacher.setPassword(passwordEncoder.encode(number));
            teacherRep.save(teacher);
        }
    }

    public void initAuthority() {
        if (authorityRep.count() == 0) {
            Authority teacher = new Authority();
            teacher.setName("教师");
            authorityRep.save(teacher);
            Authority stu = new Authority();
            stu.setName("学生");
            authorityRep.save(stu);
        }
    }
}
