package xyz.innky.graduationproject.web.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName exercise_scene_relation
 */
@TableName(value ="exercise_scene_relation")
@Data
public class ExerciseSceneRelation implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 
     */
    private Integer exerciseId;

    /**
     * 
     */
    private Integer sceneId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}