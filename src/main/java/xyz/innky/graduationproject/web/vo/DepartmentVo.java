package xyz.innky.graduationproject.web.vo;

import lombok.Data;
import xyz.innky.graduationproject.web.pojo.Department;
import xyz.innky.graduationproject.web.pojo.Teacher;

import java.util.List;

@Data
public class DepartmentVo extends Department {
    List<Teacher> teachers;
    Integer counts;
}
