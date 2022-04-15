package xyz.innky.graduationproject.web.controller.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.innky.graduationproject.common.utils.AccountUtil;
import xyz.innky.graduationproject.web.service.ExerciseService;
import xyz.innky.graduationproject.web.vo.Result;

@RestController
@RequestMapping("/student/exercise")
public class StudentExerciseController {
    @Autowired
    private ExerciseService exerciseService;

//    @GetMapping("/")
//    public Result getAllCourses(String courseName) {
//        Integer studentId = AccountUtil.getStudentId();
//        return Result.ok( courseService.getCourses(studentId, courseName));
//    }
//    @GetMapping("/{scid}/time")
//    public Result getCourseTime(@PathVariable("scid") Integer scid) {
//        return Result.ok(courseService.getCourseTime(scid));
//    }
    @GetMapping("/")
    public Result getAllExercise() {
        Integer studentId = AccountUtil.getStudentId();
        return Result.ok(exerciseService.getExercise(studentId));
    }
    @GetMapping("/{exerciseId}")
    public Result getMaterial(@PathVariable("exerciseId") Integer exerciseId) {
        return Result.ok(exerciseService.getById(exerciseId));
    }



}
