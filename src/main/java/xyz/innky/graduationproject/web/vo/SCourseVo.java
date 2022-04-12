package xyz.innky.graduationproject.web.vo;

import lombok.Data;
import xyz.innky.graduationproject.web.pojo.ClassInfo;
import xyz.innky.graduationproject.web.pojo.SCourse;

import java.util.List;

@Data
public class SCourseVo extends SCourse {
    List<ClassInfo> classInfos;
    String teacherName;
    String courseName;
}
