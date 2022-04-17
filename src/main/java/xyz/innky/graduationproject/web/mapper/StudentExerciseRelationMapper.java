package xyz.innky.graduationproject.web.mapper;
import java.util.List;
import java.util.Collection;
import org.apache.ibatis.annotations.Param;

import xyz.innky.graduationproject.web.pojo.StudentExerciseRelation;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import xyz.innky.graduationproject.web.vo.ExerciseMarkVo;

/**
* @author xingyijin
* @description 针对表【student_exercise_relation】的数据库操作Mapper
* @createDate 2022-04-06 15:13:05
* @Entity xyz.innky.graduationproject.web.pojo.StudentExerciseRelation
*/
public interface StudentExerciseRelationMapper extends BaseMapper<StudentExerciseRelation> {
    int countByExerciseIdAndSubmitTimeNotNull(@Param("exerciseId") Integer exerciseId);

    int countByExerciseId(@Param("exerciseId") Integer exerciseId);

    int countByExerciseIdAndScoreNotNull(@Param("exerciseId") Integer exerciseId);

    List<ExerciseMarkVo> getAllByExerciseIdIn(@Param("exerciseIdList") Collection<Integer> exerciseIdList);

    int updateScoreById(@Param("score") Integer score, @Param("id") Integer id);
}




