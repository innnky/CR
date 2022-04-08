package xyz.innky.graduationproject.web.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 教师信息表
 * @TableName teacher
 */
@TableName(value ="teacher")
@Data
public class Teacher implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer teacherId;

    /**
     * 所在单位id
     */
    private Integer departmentId;

    /**
     * 联系方式
     */
    private String contact;

    /**
     * 性别
     */
    private String gender;

    /**
     * 
     */
    private String teacherName;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}