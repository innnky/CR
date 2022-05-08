package xyz.innky.graduationproject.web.controller.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.innky.graduationproject.common.utils.ResultUtil;
import xyz.innky.graduationproject.web.pojo.Course;
import xyz.innky.graduationproject.web.pojo.CourseTime;
import xyz.innky.graduationproject.web.service.CourseService;
import xyz.innky.graduationproject.web.service.CourseTimeService;
import xyz.innky.graduationproject.web.vo.Result;

@RestController
@RequestMapping("/admin/course")
public class CourseController {
    @Autowired
    private CourseService courseService;


    @PostMapping("/")
    public Result addCourse(@RequestBody Course course){
        return ResultUtil.returnResultByConditionLambda(()->courseService.save(course), "添加课程");
    }
    @PutMapping("/")
    public Result updateCourse(@RequestBody Course course){
        return ResultUtil.returnResultByConditionLambda(()->courseService.updateById(course), "更新课程");
    }
    @DeleteMapping("/{id}")
    public Result deleteCourse(@PathVariable("id") Integer id){
        return ResultUtil.returnResultByConditionLambda(()->courseService.removeById(id), "删除课程");
    }
    @GetMapping("/")
    public Result getAllCourse(Integer page, Integer pageSize,
                               String courseId, String courseName){
        return Result.ok(courseService.getAllCourse(page, pageSize, courseId, courseName));
    }



}
