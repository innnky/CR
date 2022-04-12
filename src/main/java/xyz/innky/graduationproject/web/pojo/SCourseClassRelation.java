package xyz.innky.graduationproject.web.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName s_course_class_relation
 */
@TableName(value ="s_course_class_relation")
@Data
public class SCourseClassRelation implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 
     */
    private Integer sCourseId;

    /**
     * 
     */
    private Integer classId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}