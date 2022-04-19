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
 * @TableName student_exercise_relation
 */
@TableName(value ="student_exercise_relation")
@Data
public class StudentExerciseRelation implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 
     */
    private Integer studentId;

    /**
     * 
     */
    private Integer exerciseId;

    /**
     * 
     */
    private Date submitTime;

    /**
     * 
     */
    private String image;

    /**
     * 
     */
    private Integer score;

    /**
     * 
     */
    private String resultsAnalysis;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}