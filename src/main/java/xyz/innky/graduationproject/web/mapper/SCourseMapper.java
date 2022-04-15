package xyz.innky.graduationproject.web.mapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import xyz.innky.graduationproject.web.pojo.Course;
import xyz.innky.graduationproject.web.pojo.SCourse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import xyz.innky.graduationproject.web.vo.SCourseVo;

import java.util.List;

/**
* @author xingyijin
* @description 针对表【s_course】的数据库操作Mapper
* @createDate 2022-04-12 08:38:01
* @Entity xyz.innky.graduationproject.web.pojo.SCourse
*/
public interface SCourseMapper extends BaseMapper<SCourse> {

    int insertSelective(SCourseVo sCourse);

    int deleteBySCourseId(@Param("sCourseId") Integer sCourseId);

    int updateSelective(SCourse sCourse);

    List<SCourseVo> getAllSCourse(@Param("className") String className, @Param("courseName") String courseName, @Param("teacherName") String teacherName);

    Course getCourseBySid(Integer sCourseId);
}




