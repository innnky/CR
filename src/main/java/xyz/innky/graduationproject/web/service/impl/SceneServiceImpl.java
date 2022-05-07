package xyz.innky.graduationproject.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import xyz.innky.graduationproject.web.params.SceneParam;
import xyz.innky.graduationproject.web.pojo.Device;
import xyz.innky.graduationproject.web.pojo.Scene;
import xyz.innky.graduationproject.web.service.DeviceSceneRelationService;
import xyz.innky.graduationproject.web.service.DeviceService;
import xyz.innky.graduationproject.web.service.SceneService;
import xyz.innky.graduationproject.web.mapper.SceneMapper;
import org.springframework.stereotype.Service;
import xyz.innky.graduationproject.web.vo.DeviceVo;
import xyz.innky.graduationproject.web.vo.SceneVo;

import java.util.List;

/**
* @author xingyijin
* @description 针对表【scene】的数据库操作Service实现
* @createDate 2022-04-06 15:13:05
*/
@Service
@Transactional
public class SceneServiceImpl extends ServiceImpl<SceneMapper, Scene>
    implements SceneService{

    @Autowired
    DeviceSceneRelationService deviceSceneRelationService;
    @Autowired
    DeviceService deviceService;

    @Override
    public Page<Scene> getAllByConditionAndPage(Integer page, Integer pageSize, Integer sceneId, String sceneName) {
        Page<Scene> pageInfo = new Page<>(page, pageSize);
        LambdaQueryWrapper<Scene> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(!ObjectUtils.isEmpty(sceneId), Scene::getSceneId, sceneId);
        queryWrapper.like(!ObjectUtils.isEmpty(sceneName), Scene::getSceneName, sceneName);
        getBaseMapper().selectPage(pageInfo, queryWrapper);
        return pageInfo;
    }

    @Override
    public boolean addScene(SceneParam scene) {
        if (this.save(scene)) {
            if (deviceSceneRelationService.setRelation(scene.getDeviceIds(),scene.getRoles(), scene.getSceneId())){
                return true;
            }
        }
        throw new RuntimeException();
//        return false;
    }

    @Override
    public List<DeviceVo> getSceneDevice(Integer sceneId) {
        return deviceService.getAllDevicesByScene(sceneId);
    }

    @Override
    public boolean updateScene(SceneParam sceneParam) {
        if (deviceSceneRelationService.setRelation(sceneParam.getDeviceIds(), sceneParam.getRoles(), sceneParam.getSceneId())) {
            return this.updateById(sceneParam);
        }
        return false;
    }


}




