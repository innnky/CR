package xyz.innky.graduationproject.web.service;

import xyz.innky.graduationproject.web.pojo.Exercise;
import com.baomidou.mybatisplus.extension.service.IService;
import xyz.innky.graduationproject.web.vo.ExerciseMarkVo;
import xyz.innky.graduationproject.web.vo.ExerciseVo;

import java.util.List;

/**
* @author xingyijin
* @description 针对表【exercise】的数据库操作Service
* @createDate 2022-04-06 15:13:05
*/
public interface ExerciseService extends IService<Exercise> {

    List<Exercise> getExerciseBySCid(Integer scid);

    List<Exercise> getStudentExercise(Integer studentId);

    List<ExerciseVo> getTeacherExercise(Integer teacherId);

    boolean saveExercise(Exercise exercise);

    List<ExerciseMarkVo> getMark(Integer teacherId, Integer scid);

    boolean doMark(Integer id, Integer score);
}
