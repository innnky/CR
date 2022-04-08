package xyz.innky.graduationproject.web.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 学生信息
 * @TableName student
 */
@TableName(value ="student")
@Data
public class Student implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer studentId;

    /**
     * 
     */
    private String studentName;

    /**
     * 班级id
     */
    private Integer classId;

    /**
     * 入学日期
     */
    private Date admissiondate;

    /**
     * 性别
     */
    private String gender;

    /**
     * 
     */
    private String contact;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}