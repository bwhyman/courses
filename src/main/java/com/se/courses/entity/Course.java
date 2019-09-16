package com.se.courses.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    private Teacher teacher;
    @OneToMany(mappedBy = "course")
    @JsonIgnore
    private List<CourseDetail> courseDetails;
    @OneToMany(mappedBy = "course")
    private List<Experiment> experiments;
    @OneToMany(mappedBy = "course")
    private List<Homework> homeworks;
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", updatable = false,
            insertable = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime insertTime;

    public Course(long id) {
        super();
        this.id = id;
    }

    public Course() {
        // TODO Auto-generated constructor stub
    }

}
