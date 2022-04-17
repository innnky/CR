package xyz.innky.graduationproject.web.vo;

import lombok.Data;
import xyz.innky.graduationproject.web.pojo.ClassInfo;
import xyz.innky.graduationproject.web.pojo.Course;
import xyz.innky.graduationproject.web.pojo.Exercise;

import java.util.List;

@Data
public class ExerciseVo extends Exercise {
    private Course course;
    private List<ClassInfo> classInfoList;
    private Integer submittedCount;
    private Integer markedCount;
    private Integer studentCount;
}
