package xyz.innky.graduationproject.web.mapper;
import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;

import xyz.innky.graduationproject.web.pojo.ClassStudentRelation;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import xyz.innky.graduationproject.web.vo.ClassStudentVo;

/**
* @author xingyijin
* @description 针对表【class_student_relation】的数据库操作Mapper
* @createDate 2022-04-12 20:38:43
* @Entity xyz.innky.graduationproject.web.pojo.ClassStudentRelation
*/
public interface ClassStudentRelationMapper extends BaseMapper<ClassStudentRelation> {

    List<ClassStudentVo> getAllByClassId(@Param("classId") Integer classId);

    List<ClassStudentVo> getAllByStudentId(@Param("studentId") Integer studentId);

    int insertSelective(ClassStudentRelation classStudentRelation);

    int updateEndTimeByStudentId(@Param("endTime") Date endTime, @Param("studentId") Integer studentId);
}




