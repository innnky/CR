package xyz.innky.graduationproject.web.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @TableName scene
 */
@TableName(value ="scene")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Scene implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer sceneId;

    /**
     * 
     */
    private String sceneName;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}