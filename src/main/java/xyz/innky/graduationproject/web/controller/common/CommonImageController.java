package xyz.innky.graduationproject.web.controller.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import xyz.innky.graduationproject.common.utils.ResultUtil;
import xyz.innky.graduationproject.openstack.api.ImageApi;
import xyz.innky.graduationproject.web.pojo.Image;
import xyz.innky.graduationproject.web.service.ImageService;
import xyz.innky.graduationproject.web.vo.ImageVo;
import xyz.innky.graduationproject.web.vo.Result;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/common/image")
public class CommonImageController {
    @Autowired
    private ImageService imageService;
    @Autowired
    private ImageApi api;


    @PostMapping("/")
    public Result uploadImage(@RequestBody ImageVo image) {
        File file1 = new File("/Users/xingyijin/IdeaProjects/graduationProject/src/main/resources/static/" + image.getFileName());
        String uuid = api.createImage(file1, image.getImageName());
        image.setUuid(uuid);
        return ResultUtil.returnResultByCondition(imageService.save(image), "上传镜像");
    }

    @PutMapping("/")
    public Result updateImage(@RequestBody Image image) {
        return ResultUtil.returnResultByCondition(imageService.updateById(image), "更新镜像");
    }

    @DeleteMapping("/{id}/{uuid}")
    public Result deleteImage(@PathVariable("id") Integer id,@PathVariable("uuid") String uuid) {
        api.deleteImage(uuid);
        return ResultUtil.returnResultByCondition(imageService.removeById(id), "删除镜像");
    }

    @GetMapping("/")
    public Result getImage(Integer page, Integer pageSize,
                           String imageName, String imageType, Integer imageId) {
        return Result.ok(imageService.getAllImagesOnConditionAndPage(page, pageSize, imageName, imageType, imageId));
    }
    @PostMapping("/file")
    public Result uploadFile(MultipartFile file){
        try {
            File file1 = new File("/Users/xingyijin/IdeaProjects/graduationProject/src/main/resources/static/" + file.getOriginalFilename());
            file.transferTo(file1);
//            api.createImage(file1, "sadasddsdsdsad");
            return Result.ok("上传成功");
        } catch (IOException e) {
            e.printStackTrace();
            return Result.err("上传失败");
        }
    }
}
