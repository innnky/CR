package xyz.innky.graduationproject.web.controller.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.innky.graduationproject.common.utils.AccountUtil;
import xyz.innky.graduationproject.web.service.MaterialService;
import xyz.innky.graduationproject.web.vo.Result;

@RestController
@RequestMapping("/student/material")
public class StudentMaterialController {
//    @GetMapping("/")
//    public Result getAllExercise() {
//        Integer studentId = AccountUtil.getStudentId();
//        return Result.ok(exerciseService.getExercise(studentId));
//    }
    @Autowired
    private MaterialService materialService;
    @GetMapping("/")
    public Result getAllMaterial() {
        Integer studentId = AccountUtil.getStudentId();
        return Result.ok(materialService.getMaterial(studentId));
    }


}
