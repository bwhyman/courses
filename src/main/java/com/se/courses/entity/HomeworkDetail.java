package com.se.courses.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
@Entity
@Data
public class HomeworkDetail {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Lob
	@Column(columnDefinition = "TEXT")
	private String solution;
	@ManyToOne
	private Homework homework;
	@ManyToOne
	private Student student;
	// 完成时间
	//@Temporal(TemporalType.TIMESTAMP)
    @Column(columnDefinition="TIMESTAMP")
	private LocalDateTime completeTime;

}
