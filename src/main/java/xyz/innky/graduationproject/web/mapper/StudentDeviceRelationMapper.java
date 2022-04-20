package xyz.innky.graduationproject.web.mapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

import xyz.innky.graduationproject.web.pojo.StudentDeviceRelation;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import xyz.innky.graduationproject.web.vo.StudentDeviceReservation;

/**
* @author xingyijin
* @description 针对表【student_device_relation】的数据库操作Mapper
* @createDate 2022-04-19 20:12:03
* @Entity xyz.innky.graduationproject.web.pojo.StudentDeviceRelation
*/
public interface StudentDeviceRelationMapper extends BaseMapper<StudentDeviceRelation> {

    List<StudentDeviceReservation> getAllByStudentId(@Param("studentId") Integer studentId);

}




