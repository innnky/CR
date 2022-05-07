package xyz.innky.graduationproject.web.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.innky.graduationproject.common.utils.ResultUtil;
import xyz.innky.graduationproject.web.pojo.ClassInfo;
import xyz.innky.graduationproject.web.service.ClassInfoService;
import xyz.innky.graduationproject.web.vo.Result;


@RestController
@RequestMapping("/admin/class")
public class ClassController {

    @Autowired
    ClassInfoService classInfoService;
    @GetMapping("/")
    Result getAllClass(Integer page, Integer pageSize, String college, String major, String grade, String className){
        return Result.ok(classInfoService.getAllClass(page, pageSize, college, major, grade, className));
    }

    @GetMapping("/{id}/student")
    Result getClassById(@PathVariable("id") Integer id){
        return Result.ok(classInfoService.getStudentsByClass(id));
    }


    @DeleteMapping("/{classId}")
    Result deleteClass(@PathVariable("classId") String classId){
        return ResultUtil.returnResultByConditionLambda(()->classInfoService.removeById(classId), "删除");
    }
    @PutMapping("/{classId}")
    Result updateClass(@PathVariable("classId") String classId, @RequestBody ClassInfo classInfo){
        return ResultUtil.returnResultByCondition(classInfoService.updateById(classInfo), "修改");
    }
    @PostMapping("/")
    Result addClass(@RequestBody ClassInfo classInfo){
        return ResultUtil.returnResultByCondition(classInfoService.save(classInfo), "添加");
    }



}
