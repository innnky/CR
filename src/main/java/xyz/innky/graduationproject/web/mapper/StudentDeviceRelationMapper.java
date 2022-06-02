package xyz.innky.graduationproject.web.mapper;
import java.util.Date;

import org.apache.ibatis.annotations.Param;
import xyz.innky.graduationproject.web.pojo.StudentDeviceRelation;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
* @author xingyijin
* @description 针对表【student_device_relation】的数据库操作Mapper
* @createDate 2022-04-20 11:04:21
* @Entity xyz.innky.graduationproject.web.pojo.StudentDeviceRelation
*/
public interface StudentDeviceRelationMapper extends BaseMapper<StudentDeviceRelation> {

    List<StudentDeviceRelation> getAllByStudentId(@Param("studentId") Integer studentId);

    int deleteByDeviceIdAndStudentId(@Param("deviceId") Integer deviceId, @Param("studentId") Integer studentId);

    int deleteByDeviceIdAndStudentIdAndExerciseId(@Param("deviceId") Integer deviceId, @Param("studentId") Integer studentId, @Param("exerciseId") Integer exerciseId);

    StudentDeviceRelation getOneByDeviceIdAndStudentId(@Param("deviceId") Integer deviceId, @Param("studentId") Integer studentId);


    StudentDeviceRelation getOneByExerciseIdAndStudentId(@Param("exerciseId") Integer exerciseId, @Param("studentId") Integer studentId);

    List<StudentDeviceRelation> searchAllBySequenceAndDate(@Param("sequence") Integer sequence, @Param("date") Date date);
}




