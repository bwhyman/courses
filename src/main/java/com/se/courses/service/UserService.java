package com.se.courses.service;

import com.se.courses.entity.Student;
import com.se.courses.entity.Teacher;
import com.se.courses.entity.User;
import com.se.courses.exception.CourseException;
import com.se.courses.repository.StudentRepository;
import com.se.courses.repository.TeacherRepoistory;
import com.se.courses.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepository userRep;
    @Autowired
    private StudentRepository studentRep;
    @Autowired
    private TeacherRepoistory teacherRepoistory;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public void updatePassword(String number) {
        Optional.ofNullable(studentRep.find(number))
                .or(() -> {
                    throw new CourseException("学生不存在!");
                })
                .get().setPassword(passwordEncoder.encode(number));
    }
    public Student getStudent(long sid) {
        return studentRep.findById(sid).orElse(null);
    }
    public Teacher getTeacher(long tid) {
        return teacherRepoistory.findById(tid).orElse(null);
    }

    public User getUser(String number) {
        return userRep.find(number);
    }


    public void updatePassword(long id, String pwd) {
        userRep.findById(id).ifPresent(u -> {
            u.setPassword(passwordEncoder.encode(pwd));
        });
    }
}
