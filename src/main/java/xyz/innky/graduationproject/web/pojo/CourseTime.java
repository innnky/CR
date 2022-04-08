package xyz.innky.graduationproject.web.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName course_time
 */
@TableName(value ="course_time")
@Data
public class CourseTime implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer timeId;

    /**
     * 
     */
    private String term;

    /**
     * 
     */
    private Integer weekday;

    /**
     * 
     */
    private Integer week;

    /**
     * 
     */
    private Integer classSequence;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}