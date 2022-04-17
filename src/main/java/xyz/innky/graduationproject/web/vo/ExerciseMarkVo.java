package xyz.innky.graduationproject.web.vo;

import lombok.Data;
import xyz.innky.graduationproject.web.pojo.StudentExerciseRelation;

@Data
public class ExerciseMarkVo  extends StudentExerciseRelation {
    private String studentName;
    private String courseName;
    private String content;
    private String className;
}
