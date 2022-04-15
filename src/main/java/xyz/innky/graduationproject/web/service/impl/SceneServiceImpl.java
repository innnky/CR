package xyz.innky.graduationproject.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import xyz.innky.graduationproject.web.pojo.Scene;
import xyz.innky.graduationproject.web.service.DeviceSceneRelationService;
import xyz.innky.graduationproject.web.service.SceneService;
import xyz.innky.graduationproject.web.mapper.SceneMapper;
import org.springframework.stereotype.Service;
import xyz.innky.graduationproject.web.vo.SceneVo;

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
    public boolean addScene(SceneVo scene) {
        if (this.save(scene)) {
            if (deviceSceneRelationService.setRelation(scene.getDeviceIds(), scene.getSceneId())){
                return true;
            }
        }
        throw new RuntimeException();
//        return false;
    }


}




