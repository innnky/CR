package xyz.innky.graduationproject.web.controller.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.innky.graduationproject.common.utils.ResultUtil;
import xyz.innky.graduationproject.web.params.SceneParam;
import xyz.innky.graduationproject.web.pojo.Scene;
import xyz.innky.graduationproject.web.service.SceneService;
import xyz.innky.graduationproject.web.vo.Result;
import xyz.innky.graduationproject.web.vo.SceneVo;

@RestController
@RequestMapping("/common/scene")
public class CommonSceneController {
    @Autowired
    SceneService sceneService;

    @PostMapping("/")
    public Result addScene(@RequestBody SceneParam scene) {
        System.out.println(scene.getDeviceIds());
        return ResultUtil.returnResultByConditionLambda(()->sceneService.addScene(scene),"添加场景");
    }

    @PutMapping("/")
    public Result updateScene(@RequestBody SceneParam sceneParam) {
        return ResultUtil.returnResultByConditionLambda(()->sceneService.updateScene(sceneParam),"更新场景");
    }

    @DeleteMapping("/{id}")
    public Result deleteScene(@PathVariable("id") Integer id) {
        return ResultUtil.returnResultByConditionLambda(()->sceneService.deleteScene(id),"删除场景");
    }

    @GetMapping("/")
    public Result getAllScene(Integer page, Integer pageSize,
                              Integer sceneId, String sceneName){
        return Result.ok(sceneService.getAllByConditionAndPage(page, pageSize, sceneId, sceneName));
    }
    @GetMapping("/{sceneId}/devices")
    public Result getSceneDevice(@PathVariable("sceneId") Integer sceneId){
        return Result.ok(sceneService.getSceneDevice(sceneId));
    }

    @GetMapping("/list")
    public Result getAllScene(){
        return Result.ok(sceneService.list());
    }


}
