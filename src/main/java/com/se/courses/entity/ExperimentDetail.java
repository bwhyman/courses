package com.se.courses.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
@Entity
@Data
public class ExperimentDetail {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@ManyToOne
	private Experiment experiment;
	@ManyToOne
	private Student student;
	// 任务文件名称，12/2010223.docx
	private String file;
	// 完成时间
    @Column(columnDefinition="TIMESTAMP")
	private LocalDateTime completeTime;

}
