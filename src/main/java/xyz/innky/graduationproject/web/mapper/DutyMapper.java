package xyz.innky.graduationproject.web.mapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

import xyz.innky.graduationproject.web.pojo.Duty;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author xingyijin
* @description 针对表【duty】的数据库操作Mapper
* @createDate 2022-04-12 19:40:39
* @Entity xyz.innky.graduationproject.web.pojo.Duty
*/
public interface DutyMapper extends BaseMapper<Duty> {
    List<Duty> searchAllByStudentId(@Param("studentId") Integer studentId);
}




