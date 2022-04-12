package xyz.innky.graduationproject.web.controller.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.innky.graduationproject.common.utils.ResultUtil;
import xyz.innky.graduationproject.web.pojo.CourseTime;
import xyz.innky.graduationproject.web.pojo.SCourse;
import xyz.innky.graduationproject.web.service.CourseTimeService;
import xyz.innky.graduationproject.web.service.SCourseService;
import xyz.innky.graduationproject.web.vo.Result;
import xyz.innky.graduationproject.web.vo.SCourseVo;

@RestController
@RequestMapping("/admin/scourse")
public class SCourseController {

    @Autowired
    private SCourseService sCourseService;

    @Autowired
    CourseTimeService courseTimeService;

    @PostMapping("/")
    public Result addSCourse(@RequestBody SCourseVo sCourse) {
        return ResultUtil.returnResultByCondition(sCourseService.addSCourse(sCourse), "添加课程安排");
    }

    @DeleteMapping("/{id}")
    public Result delSCourse(@PathVariable Integer id){
        return ResultUtil.returnResultByCondition(sCourseService.removeSCourse(id), "删除课程安排");
    }

    @PutMapping("/")
    public Result updateSCourse(@RequestBody SCourseVo sCourse){
        return ResultUtil.returnResultByCondition(sCourseService.updateSCourse(sCourse), "更新课程安排");
    }

    @GetMapping("/")
    public Result getAllSCourse(Integer page, Integer pageSize,
                                String className, String courseName, String teacherName){
        return Result.ok(sCourseService.getAllSCourse(page, pageSize, className, courseName, teacherName));

    }

    @GetMapping("/{id}/class")
    public Result getCourseClass(@PathVariable("id") Integer id){
        return Result.ok(sCourseService.getCourseClass(id));
    }


    @GetMapping("/{id}/time")
    public Result getCourseTimeByCourse(@PathVariable("id") Integer id, Integer page, Integer pageSize){
        return Result.ok(courseTimeService.getAllByCourseId(new Page<>(page, pageSize), id));
    }

    @PostMapping("/{id}/time")
    public Result addCourseTime(@RequestBody CourseTime courseTime, @PathVariable("id") Integer id){
        courseTime.setSCourseId(id);
        return ResultUtil.returnResultByCondition(courseTimeService.save(courseTime), "添加课程时间");
    }

    @DeleteMapping("/{id}/time/{timeId}")
    public Result delCourseTime(@PathVariable("id") Integer id, @PathVariable("timeId") Integer timeId){
        return ResultUtil.returnResultByCondition(courseTimeService.removeById(timeId), "删除课程时间");
    }

    @PutMapping("/{id}/time")
    public Result updateCourseTime(@RequestBody CourseTime courseTime, @PathVariable("id") Integer id){
        courseTime.setSCourseId(id);
        return ResultUtil.returnResultByCondition(courseTimeService.updateById(courseTime), "更新课程时间");
    }






}
