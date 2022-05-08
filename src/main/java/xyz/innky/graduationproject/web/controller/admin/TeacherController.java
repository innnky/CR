package xyz.innky.graduationproject.web.controller.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.innky.graduationproject.common.utils.ResultUtil;
import xyz.innky.graduationproject.web.pojo.Teacher;
import xyz.innky.graduationproject.web.service.TeacherService;
import xyz.innky.graduationproject.web.vo.Result;
import xyz.innky.graduationproject.web.vo.TeacherVo;

@RestController
@RequestMapping("/admin/teacher")
public class TeacherController {
    @Autowired
    TeacherService teacherService;
    @PostMapping("/")
    Result addTeacher(@RequestBody Teacher teacher){
        return ResultUtil.returnResultByConditionLambda(()->teacherService.save(teacher), "新增教师");
    }

    @DeleteMapping("/{teacherId}")
    Result deleteTeacher(@PathVariable("teacherId") Integer teacherId){
        return ResultUtil.returnResultByConditionLambda(()->teacherService.removeById(teacherId), "删除教师");
    }

    @PutMapping("/")
    Result updateTeacher(@RequestBody Teacher teacher){
        return ResultUtil.returnResultByConditionLambda(()->teacherService.updateById(teacher), "更新教师");
    }

    @GetMapping("/")
    Result getTeacher(Integer page, Integer pageSize, String teacherName, Integer teacherId, String gender, Integer departmentId ){
        return Result.ok(teacherService.getAllTeacher(page,pageSize,teacherName,teacherId, gender, departmentId));
    }



}
