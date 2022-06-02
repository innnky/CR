package xyz.innky.graduationproject.web.controller.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.innky.graduationproject.common.utils.AccountUtil;
import xyz.innky.graduationproject.web.pojo.Course;
import xyz.innky.graduationproject.web.service.CourseService;
import xyz.innky.graduationproject.web.vo.Result;

import java.util.List;

@RestController
@RequestMapping("/student/course")
public class StudentCourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping("/")
    public Result getAllCourses(String courseName) {
        Integer studentId = AccountUtil.getStudentId();
        return Result.ok( courseService.getCourses(studentId, courseName));
    }
    @GetMapping("/{scid}/time")
    public Result getCourseTime(@PathVariable("scid") Integer scid) {
        return Result.ok(courseService.getCourseTime(scid));
    }

    @GetMapping("/{scid}/exercise")
    public Result getCourseExercise(@PathVariable("scid") Integer scid) {
        return Result.ok(courseService.getCourseExercise(scid));
    }

    @GetMapping("/{scid}/material")
    public Result getCourseMaterial(@PathVariable("scid") Integer scid) {
        return Result.ok(courseService.getCourseMaterial(scid));
    }

    @GetMapping("/{scid}")
    public Result getCourse(@PathVariable("scid") Integer scid) {
        return Result.ok(courseService.getCourseInfoByScid(scid));
    }

}
