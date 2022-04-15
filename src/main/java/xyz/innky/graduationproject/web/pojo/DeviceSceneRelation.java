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
 * @TableName device_scene_relation
 */
@TableName(value ="device_scene_relation")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeviceSceneRelation implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 
     */
    private String role;

    /**
     * 
     */
    private String startTime;

    /**
     * 
     */
    private String endTime;

    /**
     * 
     */
    private Integer deviceId;

    /**
     * 
     */
    private Integer sceneId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}