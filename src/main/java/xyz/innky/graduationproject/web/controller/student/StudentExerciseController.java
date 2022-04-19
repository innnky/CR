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
        return Result.ok(exerciseService.getStudentExercise(studentId));
    }
    @GetMapping("/{exerciseId}")
    public Result getMaterial(@PathVariable("exerciseId") Integer exerciseId) {
        return Result.ok(exerciseService.getById(exerciseId));
    }

    @PostMapping("/image")
    public Result submitImage(@RequestParam("images") MultipartFile[] images) {
//        System.out.println(1);

        for (MultipartFile file : images) {
            String fileName = file.getOriginalFilename();
            String filePath = fileRootPath + "imgs/" + fileName;
            try {
                file.transferTo(new java.io.File(filePath));
                return Result.ok("");

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return Result.err("上传失败");

    }

    @PostMapping("/{exerciseId}")
    public Result submitExercise(@PathVariable("exerciseId") Integer exerciseId, @RequestBody StudentExerciseRelation content) {
        Integer studentId = AccountUtil.getStudentId();
        content.setStudentId(studentId);
        content.setExerciseId(exerciseId);
        return ResultUtil.returnResultByCondition(exerciseService.submitExercise(content),"提交作业");
    }



}
