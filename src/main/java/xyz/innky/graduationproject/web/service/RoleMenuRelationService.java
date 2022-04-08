package xyz.innky.graduationproject.web.service;

import xyz.innky.graduationproject.web.pojo.RoleMenuRelation;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author xingyijin
* @description 针对表【role_menu_relation】的数据库操作Service
* @createDate 2022-04-06 15:13:05
*/
public interface RoleMenuRelationService extends IService<RoleMenuRelation> {

    List<RoleMenuRelation> getListByRoleId(Integer roleId);
}
