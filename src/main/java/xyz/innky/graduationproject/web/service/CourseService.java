package xyz.innky.graduationproject.web.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import xyz.innky.graduationproject.web.pojo.*;
import com.baomidou.mybatisplus.extension.service.IService;
import xyz.innky.graduationproject.web.vo.StuCourseVo;

import java.util.List;

/**
* @author xingyijin
* @description 针对表【course】的数据库操作Service
* @createDate 2022-04-06 15:13:05
*/
public interface CourseService extends IService<Course> {

    List<Course> getAllCoursesByStuid(Integer stuid);

    Page<Course> getAllCourse(Integer page, Integer pageSize, String courseId, String courseName);

    List<StuCourseVo> getCourses(Integer studentId, String courseName);

    List<CourseTime> getCourseTime(Integer scid);

    List<Exercise> getCourseExercise(Integer scid);

    List<Material> getCourseMaterial(Integer scid);
}
