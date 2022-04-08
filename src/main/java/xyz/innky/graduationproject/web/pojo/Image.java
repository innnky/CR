package xyz.innky.graduationproject.web.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName image
 */
@TableName(value ="image")
@Data
public class Image implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer imageId;

    /**
     * 
     */
    private String imageName;

    /**
     * 
     */
    private String imageType;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}