package xyz.innky.graduationproject.web.vo;

import lombok.Data;
import xyz.innky.graduationproject.web.pojo.ClassStudentRelation;

@Data
public class ClassStudentVo extends ClassStudentRelation {
    private String studentName;
    private String className;
}
