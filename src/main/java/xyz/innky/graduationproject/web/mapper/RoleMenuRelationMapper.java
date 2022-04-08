package xyz.innky.graduationproject.web.mapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

import xyz.innky.graduationproject.web.pojo.RoleMenuRelation;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author xingyijin
* @description 针对表【role_menu_relation】的数据库操作Mapper
* @createDate 2022-04-06 15:13:05
* @Entity xyz.innky.graduationproject.web.pojo.RoleMenuRelation
*/
public interface RoleMenuRelationMapper extends BaseMapper<RoleMenuRelation> {
    List<RoleMenuRelation> getAllByRoleId(@Param("roleId") Integer roleId);

}




