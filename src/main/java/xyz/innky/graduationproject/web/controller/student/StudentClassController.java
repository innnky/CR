package xyz.innky.graduationproject.web.controller.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.innky.graduationproject.common.utils.AccountUtil;
import xyz.innky.graduationproject.web.service.ClassInfoService;
import xyz.innky.graduationproject.web.vo.Result;

@RestController
@RequestMapping("/student/class")
public class StudentClassController {
    @Autowired
    ClassInfoService classInfoService;
//        @GetMapping("/")
//    public Result getClassStudents(){
//        Integer teacherId = AccountUtil.getTeacherId();
//        return Result.ok(classInfoService.getStudentsByTeacher(teacherId));
//    }
    @GetMapping("/")
    public Result getClassStudents(){
        Integer studentId = AccountUtil.getStudentId();
        return Result.ok(classInfoService.getStudentsByStudent(studentId));
    }
    @GetMapping("/headMaster")
    public Result getClassHeadMaster(){
        Integer studentId = AccountUtil.getStudentId();
        return Result.ok(classInfoService.getHeadMasterByStudent(studentId));
    }
}
