package com.se.courses.service;

import com.se.courses.component.FileComponent;
import com.se.courses.entity.Experiment;
import com.se.courses.entity.ExperimentDetail;
import com.se.courses.exception.CourseException;
import com.se.courses.repository.CourseRepository;
import com.se.courses.repository.ExperimentDetailRespostory;
import com.se.courses.repository.ExperimentRespository;
import com.se.courses.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Slf4j
public class ExperimentService {
    @Autowired
    private ExperimentRespository experimentRep;
    @Autowired
    private StudentRepository studentRep;
    @Autowired
    private ExperimentDetailRespostory experimentDetailRep;
    @Autowired
    private FileComponent fileComponent;

    public int getExperimentNumber(long cid, long sid) {
        return experimentRep.count(cid, sid);
    }
    public int getTeacherExperimentNumber(long cid, long tid) {
        return experimentRep.countByTeacherId(cid, tid);
    }

    public Experiment getExperiment(long expId) {
        return experimentRep.findById(expId).orElse(null);
    }

    public List<Experiment> listExperiments(long cid, long sid) {
        return experimentRep.list(sid, cid);
    }

    public List<Experiment> listTeacherExperiments(long cid, long tid) {
        return experimentRep.listByTeacherId(tid, cid);
    }

    public void updateExperiment(Experiment experiment) {
        experimentRep.refresh(experimentRep.saveAndFlush(experiment));
    }

    public void deleteExperiment(long cid, long expid) {
        fileComponent.deleteRecursively(cid + "/" + expid);
        experimentRep.deleteById(expid);
    }

    public void addExp(Experiment experiment) {
        experimentRep.save(experiment);
        experimentRep.refresh(experiment);
    }
    public ExperimentDetail getExperimentDetail(long sid, long edid) {
        return experimentDetailRep.findBy(sid, edid);
    }

    public void addExperimentDetail(long cid,long expid, long userid,
                                                 String originalFilename, byte[] bytes) {
        Experiment experiment = experimentRep
                .findById(expid)
                .orElseThrow(() -> new CourseException("实验不存在"));
        var dir = cid + "/" + expid;
        var ext = StringUtils.getFilenameExtension(originalFilename);
        var student = studentRep.findById(userid).orElse(null);
        var fileName = student.getName() + "-" + student.getNumber() + "." + ext;
        fileComponent.createExpFile(dir, fileName, bytes);
        var fullName = dir + "/" + fileName;
        Optional.ofNullable(experimentDetailRep.find(userid, expid))
                .ifPresent(ed -> experimentDetailRep.delete(ed));
        ExperimentDetail ed = new ExperimentDetail();
        ed.setStudent(student);
        ed.setExperiment(experiment);
        ed.setFile(fullName);
        experimentDetailRep.save(ed);
        experimentDetailRep.refresh(ed);
    }

    public List<ExperimentDetail> listExperimentDetails(long cid, long sid) {
        return experimentDetailRep.list(cid, sid);
    }

    /**
     * 指定实验的，未提交学生
     *
     * @param expId
     * @return
     */
    public List<String > listUnsubmitedStudents(long cid, long expId) {
        return experimentDetailRep.listStudents(cid, expId);
    }
}
