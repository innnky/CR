package xyz.innky.graduationproject.web.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import xyz.innky.graduationproject.web.pojo.Teacher;
import com.baomidou.mybatisplus.extension.service.IService;
import xyz.innky.graduationproject.web.vo.TeacherVo;

import java.util.List;

/**
* @author xingyijin
* @description 针对表【teacher(教师信息表)】的数据库操作Service
* @createDate 2022-04-06 15:13:05
*/
public interface TeacherService extends IService<Teacher> {

    Page<TeacherVo> getAllTeacher(Integer page, Integer pageSize, String teacherName, Integer teacherId, String gender, Integer departmentId);

    List<Teacher> searchAllByDepartmentId(Integer departmentId);

    Integer searchAllCountByDepartmentId(Integer departmentId);

    List<Teacher> getAllTeachersByName(String teacherName);

    Teacher getByTeacherNumberAndTeacherName(String teacherNumber, String teacherName);
}
