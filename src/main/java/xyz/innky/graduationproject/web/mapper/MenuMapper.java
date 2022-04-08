package xyz.innky.graduationproject.web.mapper;
import org.apache.ibatis.annotations.Param;

import xyz.innky.graduationproject.web.pojo.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import xyz.innky.graduationproject.web.vo.MenuVo;

import java.util.List;

/**
* @author xingyijin
* @description 针对表【menu(菜单表)】的数据库操作Mapper
* @createDate 2022-04-06 15:13:05
* @Entity xyz.innky.graduationproject.web.pojo.Menu
*/
public interface MenuMapper extends BaseMapper<Menu> {
    Menu getOneByMenuId(@Param("menuId") Integer menuId);
    List<MenuVo> getListByMenuIds(@Param("menuIds") List<Integer> menuIds);
}




