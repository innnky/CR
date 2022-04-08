package xyz.innky.graduationproject.web.vo;

import lombok.Data;
import xyz.innky.graduationproject.web.pojo.ClassInfo;
import xyz.innky.graduationproject.web.pojo.Duty;
import xyz.innky.graduationproject.web.pojo.Student;

import java.util.List;

@Data
public class StudentVo extends Student {
    private ClassInfo classInfo;
    private List<Duty> duties;
}
