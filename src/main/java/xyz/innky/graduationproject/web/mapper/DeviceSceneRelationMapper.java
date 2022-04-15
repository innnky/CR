package xyz.innky.graduationproject.web.mapper;
import java.util.Collection;
import org.apache.ibatis.annotations.Param;

import xyz.innky.graduationproject.web.pojo.DeviceSceneRelation;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author xingyijin
* @description 针对表【device_scene_relation】的数据库操作Mapper
* @createDate 2022-04-15 11:55:00
* @Entity xyz.innky.graduationproject.web.pojo.DeviceSceneRelation
*/
public interface DeviceSceneRelationMapper extends BaseMapper<DeviceSceneRelation> {

    int delBySceneId(@Param("sceneId") Integer sceneId);

    int insertBatch(@Param("deviceSceneRelationCollection") Collection<DeviceSceneRelation> deviceSceneRelationCollection);
}




