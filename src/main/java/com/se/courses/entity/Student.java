package com.se.courses.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import java.util.List;

@Entity
@Getter
@Setter
public class Student extends User {
    // 班级
    private String clazz;
    @OneToMany(mappedBy = "student")
    @OrderBy(value = "id ASC")
    private List<CourseDetail> courseDetails;
    @OneToMany(mappedBy = "student")
    private List<ExperimentDetail> experimentDetails;

    public Student() {
    }

    public Student(long id) {
        super(id);
    }
}
