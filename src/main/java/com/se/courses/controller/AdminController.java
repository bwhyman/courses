package com.se.courses.controller;

import com.se.courses.component.CommonComponent;
import com.se.courses.component.FileComponent;
import com.se.courses.entity.*;
import com.se.courses.service.CourseService;
import com.se.courses.service.ExperimentService;
import com.se.courses.service.HomeworkService;
import com.se.courses.service.UserService;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/admin")
@Slf4j
public class AdminController {
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
    @Autowired
    private ExperimentService eService;

    /*
     * ========================== COURSE =========================
     */

    @PostMapping("/courses")
    public Map postCourse(@Validated @RequestBody Course course) {
        courseService.addCourse(course, cComponent.getUserId());
        return Map.of("courses", courseService.listTeacherCourses(cComponent.getUserId()));
    }

    @PatchMapping("/courses/{cid}")
    public Map updateCourse(@RequestBody Course course) {
        courseService.updateCourse(course);
        return Map.of("courses", courseService.listTeacherCourses(cComponent.getUserId()));
    }

    /*
     *=========================== EXPERIMENT =========================
     */

    @PostMapping("/courses/{cid}/experiments")
    public Map postExp(@PathVariable long cid, @RequestBody Experiment experiment) {
        experiment.setCourse(new Course(cid));
        eService.addExp(experiment);
        return Map.of("exps", eService.listTeacherExperiments(cid, cComponent.getUserId()));
    }

    @PatchMapping("/courses/{cid}/experiments/{expid}")
    public Map updateExp(@PathVariable long cid, @RequestBody Experiment experiment) {
        log.debug("{}", experiment.getDeadLineTime());
        expService.updateExperiment(experiment);
        return Map.of("exps", eService.listTeacherExperiments(cid, cComponent.getUserId()));
    }

    @DeleteMapping("/courses/{cid}/experiments/{expid}")
    public Map deleteExp(@PathVariable long cid, @PathVariable long expid) {
        expService.deleteExperiment(cid, expid);
        return Map.of("exps", eService.listTeacherExperiments(cid, cComponent.getUserId()));
    }

    @GetMapping("courses/{cid}/experiments/{expid}/zip")
    public void getFileZip(@PathVariable long cid, @PathVariable long expid,
                           HttpServletResponse response) {
        Experiment experiment = expService.getExperiment(expid);
        String directory = cid + "/" + expid;
        String fileName = URLEncoder.encode(expid + "-" + experiment.getName() + ".zip",
                StandardCharsets.UTF_8);
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
        response.setHeader("filename", fileName);
        fileComponent.writeToOutputStream(response, directory);
    }

    @GetMapping("/courses/{cid}/experiments/{expid}/unsubmited")
    public Map listExpUnsubmited(@PathVariable long cid, @PathVariable long expid) {
        return Map.of("students", eService.listUnsubmitedStudents(cid, expid));
    }

    /*
     * ===================HOMEWOEK============================
     */
    @PostMapping("/courses/{cid}/homeworks")
    public Map postHomework(@PathVariable long cid, Homework homework, MultipartFile[] files) {
        if (files.length > 0) {

        }
        return null;

        /*homework.setCourse(new Course(cid));
        hService.addHomework(homework);
        return Map.of("homeworks", hService.listTeaherHomeworks(cid, cComponent.getUserId()));*/

    }
    /*@PostMapping("/courses/{cid}/homeworks")
    public Map postHomework(@PathVariable long cid, @RequestBody Homework homework) {
        homework.setCourse(new Course(cid));
        hService.addHomework(homework);
        return Map.of("homeworks", hService.listTeaherHomeworks(cid, cComponent.getUserId()));

    }*/

    @PatchMapping("/courses/{cid}/homeworks/{hid}")
    public Map updateHomework(@PathVariable long cid,
                              @RequestBody Homework homework) {
        hService.updateHomework(homework);
        return Map.of("homeworks", hService.listTeaherHomeworks(cid, cComponent.getUserId()));
    }

    @DeleteMapping("/courses/{cid}/homeworks/{hid}")
    public Map deleteHomework(@PathVariable long cid, @PathVariable long hid) {
        hService.deleteHomework(hid);
        return Map.of("homeworks", hService.listTeaherHomeworks(cid, cComponent.getUserId()));
    }

    @GetMapping("/courses/{cid}/homeworks/{hid}/unsubmited")
    public Map listHomeworkUnsubmited(@PathVariable long hid) {
        return Map.of("students", hService.listUnsubmitedStudents(hid));
    }
    @GetMapping("/courses/{cid}/homeworks/{hid}/students/{sid}")
    public Map getHomeworkDetail(@PathVariable long hid, @PathVariable long sid) {
        return Map.of("homeworkDetail",
                Optional.ofNullable(hService.getHomeworkDetail(hid, sid)).orElseGet(HomeworkDetail::new));
    }

    /*
     * ================  STUDENTS ==============
     * */

    @GetMapping("/courses/{cid}/students")
    public Map getStudents(@PathVariable long cid) {
        return Map.of("students", courseService.listStudents(cid, cComponent.getUserId()));
    }

    @PatchMapping("/courses/{cid}/students")
    public Map postStudents(@PathVariable long cid, @RequestBody List<Student> students) {
        courseService.addStudents(cid, students);
        return Map.of("students", courseService.listStudents(cid, cComponent.getUserId()));
    }

    /*
     * =====================
     * */

    @PutMapping("/students/{number}/password")
    public Map resetPassword(@PathVariable String number) {
        userService.updatePassword(number);
        return null;
    }
}
