package xyz.innky.graduationproject.web.mapper;

import org.apache.ibatis.annotations.Param;
import xyz.innky.graduationproject.web.pojo.Exercise;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import xyz.innky.graduationproject.web.vo.StudentExerciseVo;

import java.util.Collection;
import java.util.List;

/**
* @author xingyijin
* @description 针对表【exercise】的数据库操作Mapper
* @createDate 2022-04-15 16:38:27
* @Entity xyz.innky.graduationproject.web.pojo.Exercise
*/
public interface ExerciseMapper extends BaseMapper<Exercise> {
    List<Exercise> getAllBySCourseId(@Param("sCourseId") Integer sCourseId);

    List<Exercise> getAllBySCourseIdIn(@Param("sCourseIdList") Collection<Integer> sCourseIdList);

    List<StudentExerciseVo> getAllByStudentId(@Param("studentId") Integer studentId);

    int insertSelective(Exercise exercise);

}




