package xyz.innky.graduationproject.web.mapper;

import org.apache.ibatis.annotations.Param;
import xyz.innky.graduationproject.web.pojo.StudentExerciseRelation;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import xyz.innky.graduationproject.web.vo.ExerciseMarkVo;

import java.util.Collection;
import java.util.List;

/**
* @author xingyijin
* @description 针对表【student_exercise_relation】的数据库操作Mapper
* @createDate 2022-04-19 16:26:57
* @Entity xyz.innky.graduationproject.web.pojo.StudentExerciseRelation
*/
public interface StudentExerciseRelationMapper extends BaseMapper<StudentExerciseRelation> {
    int countByExerciseIdAndSubmitTimeNotNull(@Param("exerciseId") Integer exerciseId);

    int countByExerciseId(@Param("exerciseId") Integer exerciseId);

    int countByExerciseIdAndScoreNotNull(@Param("exerciseId") Integer exerciseId);

    List<ExerciseMarkVo> getAllByExerciseIdIn(@Param("exerciseIdList") Collection<Integer> exerciseIdList);

    int updateScoreById(@Param("score") Integer score, @Param("id") Integer id);

    int updateSelective(StudentExerciseRelation studentExerciseRelation);

}




