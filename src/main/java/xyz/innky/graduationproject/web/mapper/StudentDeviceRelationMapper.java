package xyz.innky.graduationproject.web.mapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

import xyz.innky.graduationproject.web.pojo.StudentDeviceRelation;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author xingyijin
* @description 针对表【student_device_relation】的数据库操作Mapper
* @createDate 2022-04-20 09:28:34
* @Entity xyz.innky.graduationproject.web.pojo.StudentDeviceRelation
*/
public interface StudentDeviceRelationMapper extends BaseMapper<StudentDeviceRelation> {

    List<StudentDeviceRelation> getAllByStudentId(@Param("studentId") Integer studentId);

    int deleteByDeviceIdAndStudentId(@Param("deviceId") Integer deviceId, @Param("studentId") Integer studentId);

    StudentDeviceRelation getOneByDeviceIdAndStudentId(@Param("deviceId") Integer deviceId, @Param("studentId") Integer studentId);

}




