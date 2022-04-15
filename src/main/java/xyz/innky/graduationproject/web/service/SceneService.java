package xyz.innky.graduationproject.web.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import xyz.innky.graduationproject.web.pojo.Scene;
import com.baomidou.mybatisplus.extension.service.IService;
import xyz.innky.graduationproject.web.vo.SceneVo;

/**
* @author xingyijin
* @description 针对表【scene】的数据库操作Service
* @createDate 2022-04-06 15:13:05
*/
public interface SceneService extends IService<Scene> {

    Page<Scene> getAllByConditionAndPage(Integer page, Integer pageSize, Integer sceneId, String sceneName);

    boolean addScene(SceneVo scene);
}
