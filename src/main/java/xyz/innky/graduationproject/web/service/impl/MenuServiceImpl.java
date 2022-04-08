package xyz.innky.graduationproject.web.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import xyz.innky.graduationproject.web.pojo.Menu;
import xyz.innky.graduationproject.web.service.MenuService;
import xyz.innky.graduationproject.web.mapper.MenuMapper;
import org.springframework.stereotype.Service;

/**
* @author xingyijin
* @description 针对表【menu(菜单表)】的数据库操作Service实现
* @createDate 2022-04-06 15:13:05
*/
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu>
    implements MenuService{

}




