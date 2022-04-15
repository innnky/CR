package xyz.innky.graduationproject.web.service;

import xyz.innky.graduationproject.web.pojo.ClassStudentRelation;
import com.baomidou.mybatisplus.extension.service.IService;
import xyz.innky.graduationproject.web.pojo.Student;
import xyz.innky.graduationproject.web.vo.ClassStudentVo;

import java.util.List;

/**
* @author xingyijin
* @description 针对表【class_student_relation】的数据库操作Service
* @createDate 2022-04-12 20:38:43
*/
public interface ClassStudentRelationService extends IService<ClassStudentRelation> {

    List<ClassStudentVo> getStudentsByClass(Integer id);

    List<ClassStudentVo> getHistoryClass(Integer id);

    boolean addStudent(Student student);

    boolean changeStudentClass(Student student);
}
