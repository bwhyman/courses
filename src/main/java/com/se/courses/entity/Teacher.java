package com.se.courses.entity;

import lombok.Data;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
@Entity
@Data
public class Teacher extends User{
	@OneToMany(mappedBy = "teacher")
	@OrderBy(value ="id ASC")
	private Set<Course> courses;

	public Teacher(long id) {
		super(id);
	}
	public Teacher() {
		// TODO Auto-generated constructor stub
	}
}
