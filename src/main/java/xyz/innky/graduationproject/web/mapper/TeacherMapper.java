package xyz.innky.graduationproject.web.mapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import xyz.innky.graduationproject.web.pojo.Teacher;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import xyz.innky.graduationproject.web.vo.TeacherVo;

import java.util.List;

/**
* @author xingyijin
* @description 针对表【teacher(教师信息表)】的数据库操作Mapper
* @createDate 2022-04-06 15:13:05
* @Entity xyz.innky.graduationproject.web.pojo.Teacher
*/
public interface TeacherMapper extends BaseMapper<Teacher> {
    List<Teacher> searchAllByDepartmentId(@Param("departmentId") Integer departmentId);
    Integer searchAllCountByDepartmentId(@Param("departmentId") Integer departmentId);

    Page<TeacherVo> selectPageVo(@Param("teacherVoPage") Page<TeacherVo> teacherVoPage, @Param("ew") Wrapper<TeacherVo> ew);

    List<Teacher> getAllByTeacherName(@Param("teacherName") String teacherName);
}




