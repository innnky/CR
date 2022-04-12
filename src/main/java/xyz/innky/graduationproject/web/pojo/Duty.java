package xyz.innky.graduationproject.web.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName duty
 */
@TableName(value ="duty")
@Data
public class Duty implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 
     */
    private Integer classId;

    /**
     * 
     */
    private Integer studentId;

    /**
     * 
     */
    private String dutyName;

    /**
     * 
     */
    private Date startTime;

    /**
     * 
     */
    private Date endTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}