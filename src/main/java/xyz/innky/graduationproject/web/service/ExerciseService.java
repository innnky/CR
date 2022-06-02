package xyz.innky.graduationproject.web.service;

import org.springframework.web.multipart.MultipartFile;
import xyz.innky.graduationproject.web.pojo.Exercise;
import com.baomidou.mybatisplus.extension.service.IService;
import xyz.innky.graduationproject.web.pojo.StudentExerciseRelation;
import xyz.innky.graduationproject.web.vo.ExerciseMarkVo;
import xyz.innky.graduationproject.web.vo.ExerciseVo;
import xyz.innky.graduationproject.web.vo.StudentExerciseVo;

import java.util.List;

/**
* @author xingyijin
* @description 针对表【exercise】的数据库操作Service
* @createDate 2022-04-06 15:13:05
*/
public interface ExerciseService extends IService<Exercise> {

    List<Exercise> getExerciseBySCid(Integer scid);

    List<StudentExerciseVo> getStudentExercise(Integer studentId);

    List<ExerciseVo> getTeacherExercise(Integer teacherId);

    boolean saveExercise(Exercise exercise);

    List<ExerciseMarkVo> getMark(Integer teacherId, Integer exerciseId);

    boolean doMark(Integer id, Integer score);

    boolean submitExercise(StudentExerciseRelation content);

    StudentExerciseVo getByIdAndStudentId(Integer exerciseId, Integer studentId);

    Boolean removeExercise(Integer id);
}
