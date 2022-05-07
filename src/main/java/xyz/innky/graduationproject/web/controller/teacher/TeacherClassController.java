package xyz.innky.graduationproject.web.controller.teacher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.innky.graduationproject.common.utils.AccountUtil;
import xyz.innky.graduationproject.web.service.ClassInfoService;
import xyz.innky.graduationproject.web.vo.Result;

@RestController
@RequestMapping("/teacher/class")
public class TeacherClassController {

    @Autowired
    ClassInfoService classInfoService;

    @GetMapping("/")
    public Result getClassStudents(){
        Integer teacherId = AccountUtil.getTeacherId();
        return Result.ok(classInfoService.getStudentsByTeacher(teacherId));
    }
}
