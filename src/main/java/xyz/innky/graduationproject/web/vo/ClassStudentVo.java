package xyz.innky.graduationproject.web.vo;

import lombok.Data;
import xyz.innky.graduationproject.web.pojo.ClassStudentRelation;
import xyz.innky.graduationproject.web.pojo.Student;

@Data
public class ClassStudentVo extends ClassStudentRelation {
    private String studentName;
    private String className;
    private Student student;
}
