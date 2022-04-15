package xyz.innky.graduationproject.web.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import xyz.innky.graduationproject.web.pojo.CourseTime;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author xingyijin
* @description 针对表【course_time】的数据库操作Mapper
* @createDate 2022-04-12 11:24:45
* @Entity xyz.innky.graduationproject.web.pojo.CourseTime
*/
public interface CourseTimeMapper extends BaseMapper<CourseTime> {

    Page<CourseTime> getAllByCourseId(@Param("page") Page<CourseTime> page, @Param("courseId") Integer courseId);

}




