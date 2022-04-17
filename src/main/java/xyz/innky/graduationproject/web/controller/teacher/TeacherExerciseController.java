package xyz.innky.graduationproject.web.controller.teacher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import xyz.innky.graduationproject.common.utils.AccountUtil;
import xyz.innky.graduationproject.common.utils.ResultUtil;
import xyz.innky.graduationproject.web.service.ExerciseService;
import xyz.innky.graduationproject.web.vo.Result;

import java.io.IOException;

@RestController
@RequestMapping("/teacher/exercise")
public class TeacherExerciseController {
    @Autowired
    private ExerciseService exerciseService;

    //static.path
    @Value("${static.path}")
    private String staticPath;



    @GetMapping("/")
    public Result getAllExercise() {
        Integer teacherId = AccountUtil.getTeacherId();
        return Result.ok(exerciseService.getTeacherExercise(teacherId));
    }
    @GetMapping("/{exerciseId}")
    public Result getMaterial(@PathVariable("exerciseId") Integer exerciseId) {
        return Result.ok(exerciseService.getById(exerciseId));
    }

    @GetMapping("/mark")
    public Result getMark(Integer scid) {
        Integer teacherId = AccountUtil.getTeacherId();
        return Result.ok(exerciseService.getMark(teacherId, scid));
    }

    @PostMapping("/file")
    public Result addExerciseFile(MultipartFile file) {
//        Integer teacherId = AccountUtil.getTeacherId();
        String  filePath = staticPath + file.getOriginalFilename();
        try {
            file.transferTo(new java.io.File(filePath));
            return Result.ok("");
        } catch (IOException e) {
            e.printStackTrace();
            return Result.err("上传失败");
        }
    }

    @PostMapping("/mark/{id}/{score}")
    public Result addMark(@PathVariable("id") Integer id, @PathVariable("score") Integer score) {
        return ResultUtil.returnResultByCondition(exerciseService.doMark(id, score),"评分");
    }




}
