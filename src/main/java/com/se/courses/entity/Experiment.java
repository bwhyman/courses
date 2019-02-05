package com.se.courses.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
public class Experiment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    // 实验文件扩展名
    private String fileExtension;
    @ManyToOne(fetch = FetchType.LAZY)
    private Course course;
    @OneToMany(mappedBy = "experiment", cascade = CascadeType.REMOVE)
    private List<ExperimentDetail> detail;
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", updatable = false,
            insertable = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime insertTime;

}
