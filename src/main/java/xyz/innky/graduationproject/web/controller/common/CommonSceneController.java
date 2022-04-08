package xyz.innky.graduationproject.web.controller.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.innky.graduationproject.common.utils.ResultUtil;
import xyz.innky.graduationproject.web.pojo.Scene;
import xyz.innky.graduationproject.web.service.SceneService;
import xyz.innky.graduationproject.web.vo.Result;

@RestController
@RequestMapping("/common/scene")
public class CommonSceneController {
    @Autowired
    SceneService sceneService;

    @PostMapping("/")
    public Result addScene(@RequestBody Scene scene) {
        return ResultUtil.returnResultByCondition(sceneService.save(scene),"添加场景");
    }

    @PutMapping("/")
    public Result updateScene(@RequestBody Scene scene) {
        return ResultUtil.returnResultByCondition(sceneService.updateById(scene),"更新场景");
    }

    @DeleteMapping("/{id}")
    public Result deleteScene(@PathVariable("id") Integer id) {
        return ResultUtil.returnResultByCondition(sceneService.removeById(id),"删除场景");
    }

    @GetMapping("/")
    public Result getAllScene(Integer page, Integer pageSize,
                              Integer sceneId, String sceneName){
        return Result.ok(sceneService.getAllByConditionAndPage(page, pageSize, sceneId, sceneName));
    }

}
