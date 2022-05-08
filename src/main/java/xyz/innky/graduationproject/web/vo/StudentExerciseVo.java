package xyz.innky.graduationproject.web.vo;

import lombok.Data;
import xyz.innky.graduationproject.web.pojo.Exercise;

import java.util.Date;

@Data
public class StudentExerciseVo extends Exercise {
    private Integer score;
//    submit_time
    private Date submitTime;

    private String image;
    private String resultAnalysis;
}
