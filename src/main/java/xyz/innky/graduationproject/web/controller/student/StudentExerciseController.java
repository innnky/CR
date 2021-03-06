package xyz.innky.graduationproject.web.controller.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import xyz.innky.graduationproject.common.utils.AccountUtil;
import xyz.innky.graduationproject.common.utils.ResultUtil;
import xyz.innky.graduationproject.web.pojo.StudentExerciseRelation;
import xyz.innky.graduationproject.web.service.ExerciseService;
import xyz.innky.graduationproject.web.vo.Result;

import java.io.IOException;
import java.util.Date;

@RestController
@RequestMapping("/student/exercise")
public class StudentExerciseController {
    @Autowired
    private ExerciseService exerciseService;
    @Value("${static.path}")
    String fileRootPath;
    @Value("${server.address}")
    String serverAddress;
    @Value("${server.port}")
    String serverPort;
    @GetMapping("/")
    public Result getAllExercise() {
        Integer studentId = AccountUtil.getStudentId();
        return Result.ok(exerciseService.getStudentExercise(studentId));
    }
    @GetMapping("/{exerciseId}")
    public Result getMaterial(@PathVariable("exerciseId") Integer exerciseId) {
        return Result.ok(exerciseService.getByIdAndStudentId(exerciseId, AccountUtil.getStudentId()));
    }

    @PostMapping("/image")
    public Result submitImage(@RequestParam("file") MultipartFile file) {

        String filePath = fileRootPath+"imgs/"+file.getOriginalFilename();
        try {
            file.transferTo(new java.io.File(filePath));
            return Result.ok("");

        } catch (IOException e) {
            e.printStackTrace();
        }
        return Result.err("上传失败");

    }

    @PostMapping("/{exerciseId}")
    public Result submitExercise(@PathVariable("exerciseId") Integer exerciseId, @RequestBody StudentExerciseRelation content) {
        Integer studentId = AccountUtil.getStudentId();
        content.setStudentId(studentId);
        content.setExerciseId(exerciseId);

        content.setSubmitTime(new Date(System.currentTimeMillis()));
        return ResultUtil.returnResultByConditionLambda(()->exerciseService.submitExercise(content),"提交作业");
    }
    @PostMapping("/{exerciseId}/save")
    public Result saveExercise(@PathVariable("exerciseId") Integer exerciseId, @RequestBody StudentExerciseRelation content) {
        Integer studentId = AccountUtil.getStudentId();
        content.setStudentId(studentId);
        content.setExerciseId(exerciseId);

//        content.setSubmitTime(new Date(System.currentTimeMillis()));
        return ResultUtil.returnResultByConditionLambda(()->exerciseService.submitExercise(content),"保存作业");
    }




}
