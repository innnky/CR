package xyz.innky.graduationproject.web.service;

import xyz.innky.graduationproject.web.pojo.DeviceSceneRelation;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author xingyijin
* @description 针对表【device_scene_relation】的数据库操作Service
* @createDate 2022-04-15 11:55:00
*/
public interface DeviceSceneRelationService extends IService<DeviceSceneRelation> {

    boolean setRelation(List<Integer> deviceIds, Integer sceneId);
}
