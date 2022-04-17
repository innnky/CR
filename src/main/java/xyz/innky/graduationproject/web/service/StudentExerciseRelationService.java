package xyz.innky.graduationproject.web.service;

import xyz.innky.graduationproject.web.pojo.StudentExerciseRelation;
import com.baomidou.mybatisplus.extension.service.IService;
import xyz.innky.graduationproject.web.vo.ExerciseMarkVo;

import java.util.List;

/**
* @author xingyijin
* @description 针对表【student_exercise_relation】的数据库操作Service
* @createDate 2022-04-06 15:13:05
*/
public interface StudentExerciseRelationService extends IService<StudentExerciseRelation> {

    Integer getSubmittedCount(Integer exerciseId);

    Integer getStudentCount(Integer exerciseId);

    Integer getMarkedCount(Integer exerciseId);

    List<ExerciseMarkVo> getMark(List<Integer> exercises);

    boolean doMark(Integer id, Integer score);
}
