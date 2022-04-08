package xyz.innky.graduationproject.web.mapper;

import org.apache.ibatis.annotations.Param;
import xyz.innky.graduationproject.web.pojo.Duty;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
* @author xingyijin
* @description 针对表【duty】的数据库操作Mapper
* @createDate 2022-04-07 08:56:20
* @Entity xyz.innky.graduationproject.web.pojo.Duty
*/
public interface DutyMapper extends BaseMapper<Duty> {
    List<Duty> searchAllByStudentId(@Param("studentId") Integer studentId);
}




