package xyz.innky.graduationproject.web.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName device
 */
@TableName(value ="device")
@Data
public class Device implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer deviceId;

    /**
     * 
     */
    private Integer status;

    /**
     * 
     */
    private String deviceName;

    /**
     * 
     */
    private String imageId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}