package xyz.innky.graduationproject.web.mapper;
import java.util.Collection;
import java.util.List;
import org.apache.ibatis.annotations.Param;

import xyz.innky.graduationproject.web.pojo.Material;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author xingyijin
* @description 针对表【material】的数据库操作Mapper
* @createDate 2022-04-14 08:17:43
* @Entity xyz.innky.graduationproject.web.pojo.Material
*/
public interface MaterialMapper extends BaseMapper<Material> {

    List<Material> getAllBySCourseId(@Param("sCourseId") Integer sCourseId);

    List<Material> getAllBySCourseIdIn(@Param("sCourseIdList") Collection<Integer> sCourseIdList);
}




