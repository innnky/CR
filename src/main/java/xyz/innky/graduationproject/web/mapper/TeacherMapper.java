package xyz.innky.graduationproject.web.mapper;

import xyz.innky.graduationproject.web.pojo.Teacher;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import xyz.innky.graduationproject.web.vo.TeacherVo;

import java.util.List;

/**
* @author xingyijins
* @description 针对表【teacher(教师信息表)】的数据库操作Mapper
* @createDate 2022-04-14 21:06:41
* @Entity xyz.innky.graduationproject.web.pojo.Teacher
*/
public interface TeacherMapper extends BaseMapper<Teacher> {
    List<Teacher> searchAllByDepartmentId(@Param("departmentId") Integer departmentId);
    Integer searchAllCountByDepartmentId(@Param("departmentId") Integer departmentId);

    Page<TeacherVo> selectPageVo(@Param("teacherVoPage") Page<TeacherVo> teacherVoPage, @Param("ew") Wrapper<TeacherVo> ew, @Param("departmentId") Integer departmentId);

    List<Teacher> getAllByTeacherName(@Param("teacherName") String teacherName);

    Teacher getOneByTeacherNameAndTeacherNumber(@Param("teacherName") String teacherName, @Param("teacherNumber") String teacherNumber);

    String getMaxTeacherNumberByDepartmentId(@Param("departmentId") Integer departmentId);
}




