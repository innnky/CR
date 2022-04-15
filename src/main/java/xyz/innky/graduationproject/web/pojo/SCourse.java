package xyz.innky.graduationproject.web.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName s_course
 */
@TableName(value ="s_course")
@Data
public class SCourse implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer sCourseId;

    /**
     * 
     */
    private Integer teacherId;

    /**
     * 
     */
    private Integer courseId;

    /**
     * 
     */
    private String term;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}