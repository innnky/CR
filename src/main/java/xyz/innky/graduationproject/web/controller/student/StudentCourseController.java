package xyz.innky.graduationproject.web.controller.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.innky.graduationproject.common.utils.AccountUtil;
import xyz.innky.graduationproject.web.pojo.Course;
import xyz.innky.graduationproject.web.service.CourseService;

import java.util.List;

@RestController
@RequestMapping("/student/course")
public class StudentCourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping("/")
    public List<Course> getAllCourses() {
        Integer studentId = AccountUtil.getStudentId();
        return courseService.getAllCoursesByStuid(studentId);
    }


}
