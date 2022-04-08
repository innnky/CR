package xyz.innky.graduationproject.web.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName class_info
 */
@TableName(value ="class_info")
@Data
public class ClassInfo implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer classId;

    /**
     * 
     */
    private String className;

    /**
     * 
     */
    private String grade;

    /**
     * 
     */
    private String major;

    /**
     * 
     */
    private String college;

    /**
     * 
     */
    private Integer headTeacherId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}