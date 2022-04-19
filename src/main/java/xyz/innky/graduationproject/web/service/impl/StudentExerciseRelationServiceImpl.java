package xyz.innky.graduationproject.web.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import xyz.innky.graduationproject.web.pojo.StudentExerciseRelation;
import xyz.innky.graduationproject.web.service.StudentExerciseRelationService;
import xyz.innky.graduationproject.web.mapper.StudentExerciseRelationMapper;
import org.springframework.stereotype.Service;
import xyz.innky.graduationproject.web.vo.ExerciseMarkVo;

import java.util.List;

/**
* @author xingyijin
* @description 针对表【student_exercise_relation】的数据库操作Service实现
* @createDate 2022-04-06 15:13:05
*/
@Service
public class StudentExerciseRelationServiceImpl extends ServiceImpl<StudentExerciseRelationMapper, StudentExerciseRelation>
    implements StudentExerciseRelationService{



    @Override
    public Integer getSubmittedCount(Integer exerciseId) {
        return getBaseMapper().countByExerciseIdAndSubmitTimeNotNull(exerciseId);
    }

    @Override
    public Integer getStudentCount(Integer exerciseId) {
        return getBaseMapper().countByExerciseId(exerciseId);
    }

    @Override
    public Integer getMarkedCount(Integer exerciseId) {
        return getBaseMapper().countByExerciseIdAndScoreNotNull(exerciseId);
    }

    @Override
    public List<ExerciseMarkVo> getMark(List<Integer> exercises) {
        return getBaseMapper().getAllByExerciseIdIn(exercises);
    }

    @Override
    public boolean doMark(Integer id, Integer score) {
        return getBaseMapper().updateScoreById(score, id) == 1;
    }

    @Override
    public boolean submitExercise(StudentExerciseRelation content) {
        return getBaseMapper().updateSelective(content) == 1;
    }
}




