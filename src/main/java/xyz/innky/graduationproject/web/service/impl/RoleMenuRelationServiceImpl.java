package xyz.innky.graduationproject.web.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import xyz.innky.graduationproject.web.pojo.RoleMenuRelation;
import xyz.innky.graduationproject.web.service.RoleMenuRelationService;
import xyz.innky.graduationproject.web.mapper.RoleMenuRelationMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author xingyijin
* @description 针对表【role_menu_relation】的数据库操作Service实现
* @createDate 2022-04-06 15:13:05
*/
@Service
public class RoleMenuRelationServiceImpl extends ServiceImpl<RoleMenuRelationMapper, RoleMenuRelation>
    implements RoleMenuRelationService{

    @Override
    public List<RoleMenuRelation> getListByRoleId(Integer roleId) {
        return getBaseMapper().getAllByRoleId(roleId);
    }
}




