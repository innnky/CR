package xyz.innky.graduationproject.web.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.multipart.MultipartFile;
import xyz.innky.graduationproject.web.pojo.*;
import com.baomidou.mybatisplus.extension.service.IService;
import xyz.innky.graduationproject.web.vo.CourseVo;
import xyz.innky.graduationproject.web.vo.Result;

import java.util.List;

/**
* @author xingyijin
* @description 针对表【course】的数据库操作Service
* @createDate 2022-04-06 15:13:05
*/
public interface CourseService extends IService<Course> {

    List<Course> getAllCoursesByStuid(Integer stuid);

    Page<Course> getAllCourse(Integer page, Integer pageSize, String courseId, String courseName);

    List<CourseVo> getCourses(Integer studentId, String courseName);

    List<CourseTime> getCourseTime(Integer scid);

    List<Exercise> getCourseExercise(Integer scid);

    List<Material> getCourseMaterial(Integer scid);

    List<CourseVo> getTeacherCourses(Integer teacherId);

    boolean addCourseMaterial(Integer scid, MultipartFile material);

    boolean addCourseExercise(Exercise exercise);

    Course getCourseInfoByScid(Integer scid);
}
