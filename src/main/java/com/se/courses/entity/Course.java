package com.se.courses.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Data
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @ManyToOne
    private Teacher teacher;
    @OneToMany(mappedBy = "course")
    @OrderBy(value = "id ASC")
    private Set<CourseDetail> courseDetails;
    @OrderBy(value = "id ASC")
    @OneToMany(mappedBy = "course")
    private Set<Experiment> experiments;
    @OrderBy(value = "id ASC")
    @OneToMany(mappedBy = "course")
    private Set<Homework> homeworks;

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime insertTime;

    public Course(long id) {
        super();
        this.id = id;
    }

    public Course() {
        // TODO Auto-generated constructor stub
    }

}
