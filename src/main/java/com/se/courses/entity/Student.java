package com.se.courses.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import java.util.Set;
@Entity
@Data
public class Student extends User{
	// 班级
	private String clazz;
	@OneToMany(mappedBy = "student")
	@OrderBy(value ="id ASC")
	private Set<CourseDetail> courseDetails;
	@OneToMany(mappedBy = "student")
	@OrderBy(value ="id ASC")
	private Set<ExperimentDetail> experimentDetails;
}
