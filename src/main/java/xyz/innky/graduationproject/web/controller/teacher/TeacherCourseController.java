package xyz.innky.graduationproject.web.controller.teacher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import xyz.innky.graduationproject.common.utils.AccountUtil;
import xyz.innky.graduationproject.common.utils.ResultUtil;
import xyz.innky.graduationproject.web.pojo.Exercise;
import xyz.innky.graduationproject.web.service.CourseService;
import xyz.innky.graduationproject.web.vo.Result;

@RestController
@RequestMapping("/teacher/course")
public class TeacherCourseController {
    @Autowired
    private CourseService courseService;
    //address
    @Value("${server.address}")
    private String serverAddress;
    //port
    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/")
    public Result getTeacherCourses() {
        Integer teacherId = AccountUtil.getTeacherId();
        return Result.ok(courseService.getTeacherCourses(teacherId));
    }
    @GetMapping("/{scid}/time")
    public Result getCourseTime(@PathVariable("scid") Integer scid) {
        return Result.ok(courseService.getCourseTime(scid));
    }

    @GetMapping("/{scid}/exercise")
    public Result getCourseExercise(@PathVariable("scid") Integer scid) {
        return Result.ok(courseService.getCourseExercise(scid));
    }

    @GetMapping("/{scid}/material")
    public Result getCourseMaterial(@PathVariable("scid") Integer scid) {
        return Result.ok(courseService.getCourseMaterial(scid));
    }

    @PostMapping("/{scid}/material")
    public Result addCourseMaterial(@PathVariable("scid") Integer scid, MultipartFile file) {

//        courseService.addCourseMaterial(scid, material);
//        return Result.ok();
        return ResultUtil.returnResultByConditionLambda(()->courseService.addCourseMaterial(scid, file), "添加课程资料");
    }

    @PostMapping("/{scid}/exercise")
    public Result addCourseExercise(@PathVariable("scid") Integer scid ,@RequestBody Exercise exercise) {
        exercise.setSCourseId(scid);
        exercise.setAttachmentPath("http://" + serverAddress + ":" + serverPort + "/exercise/" +exercise.getAttachmentPath());
        return ResultUtil.returnResultByConditionLambda(()->courseService.addCourseExercise(exercise), "添加课程作业");
    }

//    @PostMapping("/exercise/file")


}
