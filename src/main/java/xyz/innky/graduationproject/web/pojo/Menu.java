package xyz.innky.graduationproject.web.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 菜单表
 * @TableName menu
 */
@TableName(value ="menu")
@Data
public class Menu implements Serializable {
    /**
     * 
     */
    @TableId
    private Integer menuId;

    /**
     * 前端vue路由路径
     */
    private String vuePath;

    /**
     * 菜单名称
     */
    private String menuName;

    /**
     * 父级菜单id
     */
    private Integer parentId;

    /**
     * 
     */
    private String icon;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}