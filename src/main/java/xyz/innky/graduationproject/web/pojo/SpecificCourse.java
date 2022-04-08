package xyz.innky.graduationproject.web.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName specific_course
 */
@TableName(value ="specific_course")
@Data
public class SpecificCourse implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer sCourseId;

    /**
     * 
     */
    private String sCourseName;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}