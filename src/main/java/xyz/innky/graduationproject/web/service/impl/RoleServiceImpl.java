package xyz.innky.graduationproject.web.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import xyz.innky.graduationproject.web.pojo.Role;
import xyz.innky.graduationproject.web.service.RoleService;
import xyz.innky.graduationproject.web.mapper.RoleMapper;
import org.springframework.stereotype.Service;

/**
* @author xingyijin
* @description 针对表【role(角色)】的数据库操作Service实现
* @createDate 2022-04-06 15:13:05
*/
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role>
    implements RoleService{

}




