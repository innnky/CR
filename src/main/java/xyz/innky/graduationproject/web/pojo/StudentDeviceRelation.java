package xyz.innky.graduationproject.web.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName student_device_relation
 */
@TableName(value ="student_device_relation")
@Data
public class StudentDeviceRelation implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 
     */
    private String startTime;

    /**
     * 
     */
    private String endTime;

    /**
     * 
     */
    private Integer sCourseId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}