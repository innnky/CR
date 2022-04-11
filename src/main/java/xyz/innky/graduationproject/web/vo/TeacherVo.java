package xyz.innky.graduationproject.web.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import xyz.innky.graduationproject.web.pojo.Teacher;

@Data
public class TeacherVo extends Teacher {
    private String departmentName;
}
