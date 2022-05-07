package xyz.innky.graduationproject.web.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import xyz.innky.graduationproject.web.pojo.Student;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import xyz.innky.graduationproject.web.vo.StudentVo;

import java.util.List;

/**
* @author xingyijin
* @description 针对表【student(学生信息)】的数据库操作Mapper
* @createDate 2022-04-15 08:33:27
* @Entity xyz.innky.graduationproject.web.pojo.Student
*/
public interface StudentMapper extends BaseMapper<Student> {
    List<StudentVo> selectByClassId(@Param("classId") Integer classId);

    Long selectCountByClassId(Integer classId);

    Page<StudentVo> selectPageVo(@Param("studentVoPage") Page<StudentVo> studentVoPage, @Param("ew") Wrapper<StudentVo> ew);

    List<Student> selectByStudentName(@Param("studentName") String studentName);

    int insertSelective(Student student);

    int updateClassIdByStudentId(@Param("classId") Integer classId, @Param("studentId") Integer studentId);

    Student getOneByStudentNameAndStudentNumber(@Param("studentName") String studentName, @Param("studentNumber") String studentNumber);

}




