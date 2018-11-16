package com.se.courses.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
@Entity
@Data
public class Homework {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String title;
	private String content;
	@ManyToOne
	private Course course;
	@Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private LocalDateTime insertTime;
	public Homework(long id) {
		super();
		this.id = id;
	}
	public Homework() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
