package com.se.courses.controller;

import com.se.courses.component.CommonComponent;
import com.se.courses.component.FileComponent;
import com.se.courses.entity.Course;
import com.se.courses.entity.Experiment;
import com.se.courses.entity.ExperimentDetail;
import com.se.courses.entity.HomeworkDetail;
import com.se.courses.exception.CourseException;
import com.se.courses.service.CourseService;
import com.se.courses.service.ExperimentService;
import com.se.courses.service.HomeworkService;
import com.se.courses.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@Slf4j
public class UserController {
    @Autowired
    private CourseService courseService;
    @Autowired
    private HomeworkService hService;
    @Autowired
    private ExperimentService expService;
    @Autowired
    private UserService userService;
    @Autowired
    private FileComponent fileComponent;
    @Autowired
    private CommonComponent cComponent;

    /*
    * =================  COURSE  ==============
    * */

    @GetMapping("/main")
    public Map getMain() {
        if (cComponent.getAuthorityId() == 1) {
            return Map.of("courses", courseService.listTeacherCourses(cComponent.getUserId()));
        }
        return Map.of("courses", courseService.listStudentCourses(cComponent.getUserId()));
    }

    @GetMapping("/courses/{cid}")
    public Map getCourse(@PathVariable long cid) {

        if (cComponent.getAuthorityId() == 1) {
            Course course = courseService.getTeacherCourse(cid, cComponent.getUserId());
            int expNum = expService.getTeacherExperimentNumber(cid, cComponent.getUserId());
            int homewokdNum = hService.getTeacherHomeworkNumber(cid, cComponent.getUserId());
            int studentNum = courseService.getStudentNumber(cid, cComponent.getUserId());
            return Map.of("expNum", expNum,
                    "homeworkNum", homewokdNum,
                    "studentNum", studentNum,
                    "course", course);
        }
        Course course = courseService.getStudentCourse(cid, cComponent.getUserId());
        int expNum = expService.getExperimentNumber(cid, cComponent.getUserId());
        int homewokdNum = hService.getHomeworkNumber(cid, cComponent.getUserId());
        return Map.of("expNum", expNum, "homeworkNum", homewokdNum, "course", course);
    }

    /*
    * ================ EXPERIMENT ==============
    */

    @GetMapping("/courses/{cid}/experiments")
    public Map getExps(@PathVariable long cid) {
        if (cComponent.getAuthorityId() == 1) {
            return Map.of("exps", expService.listTeacherExperiments(cid, cComponent.getUserId()));
        }
        return Map.of("exps", expService.listExperiments(cid, cComponent.getUserId()));
    }

    @PostMapping("courses/{cid}/experiments/{expid}/file")
    public Map addExps(@PathVariable long cid, @PathVariable long expid, MultipartFile file) throws IOException {
        expService.addExperimentDetail(cid, expid, cComponent.getUserId(), file.getOriginalFilename(),
                file.getBytes());
        return Map.of("expDetails", expService.listExperimentDetails(cid, cComponent.getUserId()));
    }

    @GetMapping("/courses/{cid}/experiments/{expid}/experimentdetails/{edid}/file")
    public ResponseEntity<byte[]> dowloadExpFile(@PathVariable long edid) {
        return Optional.ofNullable(expService.getExperimentDetail(cComponent.getUserId(), edid))
                .map(ed -> fileComponent.toResponseEntity(ed.getFile()))
                .orElseThrow(() -> new CourseException("文件不存在"));
    }

    @GetMapping("/courses/{cid}/experimentdetails")
    public Map listExpDetails(@PathVariable long cid) {
        return Map.of("expDetails", expService.listExperimentDetails(cid, cComponent.getUserId()));
    }

    /*
     * =================  HOMEWORK  =================
     * */

    @GetMapping("/courses/{cid}/homeworks")
    public Map getHomeworks(@PathVariable long cid) {
        if (cComponent.getAuthorityId() == 1) {
            return Map.of("homeworks", hService.listTeaherHomeworks(cid, cComponent.getUserId()));
        }
        return Map.of("homeworks", hService.listHomeworks(cid, cComponent.getUserId()));
    }
    @GetMapping("/courses/{cid}/homeworkdetails")
    public Map listHomeworkDetail(@PathVariable long cid) {
        return Map.of("homeworkDetails", hService.listStudentHomeworkDetails(cid,
                cComponent.getUserId()));
    }

    @PatchMapping("/courses/{cid}/homeworks/{hid}/homeworkdetail")
    public Map patchHomeworkDetail(@PathVariable long cid,@PathVariable long hid,
                                    @RequestBody HomeworkDetail detail) {

            hService.addHomeworkDetail(hid, cComponent.getUserId(), detail);
        return Map.of("homeworkDetails", hService.listStudentHomeworkDetails(cid,
                cComponent.getUserId()));
    }
    /**
     * ====================
     */

    @GetMapping("/setting")
    public Map getSetting() {
        if(cComponent.getAuthorityId() ==1) {
            return Map.of("user", userService.getTeacher(cComponent.getUserId()));
        }
        return Map.of("user", userService.getStudent(cComponent.getUserId()));
    }

    @PatchMapping("/updatepassword")
    public void updatePassword(@RequestBody Map<String, String> map) {
        String pwd = map.get("password");
        log.debug(pwd);
        userService.updatePassword(cComponent.getUserId(), pwd);
    }
}
