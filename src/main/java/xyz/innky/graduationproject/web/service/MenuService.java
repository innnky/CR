package xyz.innky.graduationproject.web.service;

import xyz.innky.graduationproject.web.pojo.Menu;
import com.baomidou.mybatisplus.extension.service.IService;
import xyz.innky.graduationproject.web.vo.MenuVo;

import java.util.List;

/**
* @author xingyijin
* @description 针对表【menu(菜单表)】的数据库操作Service
* @createDate 2022-04-06 15:13:05
*/
public interface MenuService extends IService<Menu> {

    List<MenuVo> getMenusByMenuIds(List<Integer> menuIds);
}
