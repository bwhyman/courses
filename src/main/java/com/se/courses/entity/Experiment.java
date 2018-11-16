package com.se.courses.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;
@Entity
@Data
public class Experiment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String name;
	// 实验文件扩展名
	private String fileExtension;
	@ManyToOne
	private Course course;
	@OneToMany(mappedBy = "experiment")
	private Set<ExperimentDetail> detail;
	@Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private LocalDateTime insertTime;

}
