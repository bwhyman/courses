package com.se.courses.service;

import com.se.courses.entity.*;
import com.se.courses.exception.CourseException;
import com.se.courses.repository.CourseDetailRepository;
import com.se.courses.repository.CourseRepository;
import com.se.courses.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@Slf4j
public class CourseService {

    @Autowired
    private CourseRepository courseRep;

    @Autowired
    private StudentRepository studentRep;
    @Autowired
    private CourseDetailRepository courseDetailRep;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public int getStudentNumber(long cid, long tid) {
        return studentRep.count(cid, tid);
    }

    public void addCourse(Course course, long tid) {
        course.setTeacher(new Teacher(tid));
        courseRep.save(course);
        courseRep.refresh(course);
    }

    public Course getTeacherCourse(long cid, long tid) {
        return courseRep.find(cid, tid);
    }

    public Course getStudentCourse(long cid, long sid) {
        return courseDetailRep.find(cid, sid);
    }

    public void updateCourse(Course course) {
        courseRep.findById(course.getId())
                .orElseThrow(() -> new CourseException("课程不存在"))
                .setName(course.getName());
    }

    public List<Course> listTeacherCourses(long tId) {
        return courseRep.list(tId);
    }

    public List<Student> listStudents(long cid, long tid) {
        return courseDetailRep.listStudents(cid, tid);
    }

    public List<Course> listStudentCourses(long sId) {
        return courseDetailRep.list(sId);
    }

    public List<Student> addStudents(long courseId, List<Student> students) {
        Course course = courseRep.findById(courseId)
                .or(() -> {
                    throw new CourseException("课程不存在");
                })
                .get();

        // 清空course原detail
        courseDetailRep.deleteAll(course.getCourseDetails());

        for (int i = 0; i < students.size(); i++) {
            Student s = students.get(i);
            Student oldStu = studentRep.find(s.getNumber());
            // not exist
            if (oldStu == null) {
                students.get(i).setPassword(passwordEncoder.encode(students.get(i).getNumber()));
                students.get(i).setAuthority(new Authority(Authority.STUDENT_ID));
                studentRep.save(students.get(i));
                //studentRep.refresh(students.get(i));
            } else {
                students.set(i, oldStu);
            }
            CourseDetail detail = new CourseDetail();
            detail.setCourse(course);
            detail.setStudent(students.get(i));
            courseDetailRep.save(detail);
            //courseDetailRep.refresh(detail);
        }
        return students;
    }


}
