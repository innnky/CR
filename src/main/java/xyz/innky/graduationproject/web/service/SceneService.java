package xyz.innky.graduationproject.web.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import xyz.innky.graduationproject.web.params.SceneParam;
import xyz.innky.graduationproject.web.pojo.Device;
import xyz.innky.graduationproject.web.pojo.Scene;
import com.baomidou.mybatisplus.extension.service.IService;
import xyz.innky.graduationproject.web.vo.DeviceVo;
import xyz.innky.graduationproject.web.vo.SceneVo;

import java.util.List;

/**
* @author xingyijin
* @description 针对表【scene】的数据库操作Service
* @createDate 2022-04-06 15:13:05
*/
public interface SceneService extends IService<Scene> {

    Page<Scene> getAllByConditionAndPage(Integer page, Integer pageSize, Integer sceneId, String sceneName);

    boolean addScene(SceneParam scene);

    List<DeviceVo> getSceneDevice(Integer sceneId);

    boolean updateScene(SceneParam sceneParam);

    Boolean deleteScene(Integer id);
}
