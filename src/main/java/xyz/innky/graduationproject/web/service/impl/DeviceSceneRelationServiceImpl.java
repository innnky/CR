package xyz.innky.graduationproject.web.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;
import xyz.innky.graduationproject.web.pojo.DeviceSceneRelation;
import xyz.innky.graduationproject.web.service.DeviceSceneRelationService;
import xyz.innky.graduationproject.web.mapper.DeviceSceneRelationMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
* @author xingyijin
* @description 针对表【device_scene_relation】的数据库操作Service实现
* @createDate 2022-04-15 11:55:00
*/
@Service
@Transactional
public class DeviceSceneRelationServiceImpl extends ServiceImpl<DeviceSceneRelationMapper, DeviceSceneRelation>
    implements DeviceSceneRelationService{

    @Override
    public boolean setRelation(List<Integer> deviceIds, List<String> roles, Integer sceneId) {

        // 先删除原有的关系
        getBaseMapper().delBySceneId(sceneId);
        // 再添加新的关系
        for (int i = 0; i < deviceIds.size(); i++) {
            getBaseMapper().insert(new DeviceSceneRelation(null, roles.get(i), null, null, deviceIds.get(i), sceneId));
        }
        return true;
    }
}




