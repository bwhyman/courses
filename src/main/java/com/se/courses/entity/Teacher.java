package com.se.courses.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Getter
@Setter
public class Teacher extends User {
    @OneToMany(mappedBy = "teacher")
    private List<Course> courses;

    public Teacher(long id) {
        super(id);
    }

    public Teacher() {
        // TODO Auto-generated constructor stub
    }
}
