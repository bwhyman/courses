package com.se.courses.service;

import com.se.courses.entity.*;
import com.se.courses.exception.CourseException;
import com.se.courses.repository.CourseRepository;
import com.se.courses.repository.HomeworkDetailRepository;
import com.se.courses.repository.HomeworkReposityory;
import com.se.courses.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Slf4j
public class HomeworkService {
    @Autowired
    private HomeworkDetailRepository hdRep;
    @Autowired
    private HomeworkReposityory homeRep;

    public int getHomeworkNumber(long cid, long sid) {
        return homeRep.count(cid, sid);
    }
    public int getTeacherHomeworkNumber(long cid, long tid) {
    return homeRep.countByTeacherId(cid, tid);
    }

    public List<Homework> listTeaherHomeworks(long cid, long tid) {
        return homeRep.listByTeacherId(cid, tid);
    }
    public void addHomework(Homework homework) {
        homeRep.save(homework);
        homeRep.refresh(homework);
    }
    public Homework getHomework(long hid) {
        return homeRep.findById(hid).orElse(null);
    }

    public List<Homework> listHomeworks(long cid, long sid) {
        return homeRep.list(cid, sid);
    }

    public void updateHomework(Homework homework) {
        homeRep.refresh(homeRep.saveAndFlush(homework));
    }
    public void deleteHomework(long hid) {
        homeRep.deleteById(hid);
    }

    public List<HomeworkDetail> listStudentHomeworkDetails(long cid, long sid) {
        return hdRep.list(cid, sid);
    }

    public void addHomeworkDetail(long hid, long sid, HomeworkDetail detail) {
        Optional.ofNullable(hdRep.find(hid, sid))
                .or(() -> {
                    HomeworkDetail d = new HomeworkDetail();
                    d.setHomework(new Homework(hid));
                    d.setStudent(new Student(sid));
                    return Optional.of(d);
                })
                .ifPresent(hd -> {
                    hd.setSolution(detail.getSolution());
                    hdRep.saveAndFlush(hd);
                    hdRep.refresh(hd);
                });
    }

    public List<String > listUnsubmitedStudents(long hid) {
        return homeRep.findById(hid)
                .map(h -> hdRep.listStudents(h.getCourse().getId(), hid))
                .orElse(List.of());
    }

    public HomeworkDetail getHomeworkDetail(long hid, long sid) {
        return hdRep.find(hid, sid);
    }

}
