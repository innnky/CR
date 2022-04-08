package xyz.innky.graduationproject.web.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName material
 */
@TableName(value ="material")
@Data
public class Material implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer materialId;

    /**
     * 
     */
    private String materialName;

    /**
     * 
     */
    private Integer sCourseId;

    /**
     * 
     */
    private String updateTime;

    /**
     * 
     */
    private String materialPath;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}