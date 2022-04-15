package xyz.innky.graduationproject.web.mapper;
import java.util.List;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import xyz.innky.graduationproject.web.pojo.Course;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import xyz.innky.graduationproject.web.pojo.CourseTime;

/**
* @author xingyijin
* @description 针对表【course】的数据库操作Mapper
* @createDate 2022-04-06 15:13:05
* @Entity xyz.innky.graduationproject.web.pojo.Course
*/
public interface CourseMapper extends BaseMapper<Course> {
    Page<CourseTime> getAllByCourseId(@Param("page") Page<CourseTime> page, @Param("courseId") Integer courseId);

}




