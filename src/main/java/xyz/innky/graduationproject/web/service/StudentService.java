package xyz.innky.graduationproject.web.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import xyz.innky.graduationproject.web.pojo.Student;
import com.baomidou.mybatisplus.extension.service.IService;
import xyz.innky.graduationproject.web.vo.ClassStudentVo;
import xyz.innky.graduationproject.web.vo.StudentVo;

import java.util.List;

/**
* @author xingyijin
* @description 针对表【student(学生信息)】的数据库操作Service
* @createDate 2022-04-06 15:13:05
*/
public interface StudentService extends IService<Student> {

    Page<StudentVo> getAllStudents(Integer page, Integer pageSize, String studentName, Integer studentId, String college, String grade, String major, String className, String gender);

    Long selectCountByClassId(Integer classId);

    List<StudentVo> getStudentsByClass(Integer id);

    List<Student> getAllStudentsByName(String studentName);

    List<ClassStudentVo> getHistoryClass(Integer id);

    boolean addStudent(Student student);

    boolean updateStudentClass(Student student);
}
