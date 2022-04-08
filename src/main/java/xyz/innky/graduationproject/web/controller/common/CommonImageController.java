package xyz.innky.graduationproject.web.controller.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.innky.graduationproject.common.utils.ResultUtil;
import xyz.innky.graduationproject.web.pojo.Image;
import xyz.innky.graduationproject.web.service.ImageService;
import xyz.innky.graduationproject.web.vo.Result;

@RestController
@RequestMapping("/common/image")
public class CommonImageController {
    @Autowired
    private ImageService imageService;

    @PostMapping("/")
    public Result uploadImage(@RequestBody Image image) {
        return ResultUtil.returnResultByCondition(imageService.save(image), "上传镜像");
    }

    @PutMapping("/")
    public Result updateImage(@RequestBody Image image) {
        return ResultUtil.returnResultByCondition(imageService.updateById(image), "更新镜像");
    }

    @DeleteMapping("/{id}")
    public Result deleteImage(@PathVariable("id") Integer id) {
        return ResultUtil.returnResultByCondition(imageService.removeById(id), "删除镜像");
    }

    @GetMapping("/")
    public Result getImage(Integer page, Integer pageSize,
                           String imageName, String imageType, Integer imageId) {
        return Result.ok(imageService.getAllImagesOnConditionAndPage(page, pageSize, imageName, imageType, imageId));
    }
}
