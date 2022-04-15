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
 * @TableName exercise
 */
@TableName(value ="exercise")
@Data
public class Exercise implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer exerciseId;

    /**
     * 
     */
    private String content;

    /**
     * 
     */
    private Date startTime;

    /**
     * 
     */
    private Date endTime;

    /**
     * 
     */
    private Integer sCourseId;

    /**
     * 
     */
    private String attachmentPath;

    /**
     * 
     */
    private Integer sceneId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}