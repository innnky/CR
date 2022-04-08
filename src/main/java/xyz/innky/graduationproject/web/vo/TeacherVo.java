package xyz.innky.graduationproject.web.vo;

import lombok.Data;
import xyz.innky.graduationproject.web.pojo.Teacher;

@Data
public class TeacherVo extends Teacher {
    private String departmentName;
}
