package xyz.innky.graduationproject.web.controller.teacher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import xyz.innky.graduationproject.common.utils.ResultUtil;
import xyz.innky.graduationproject.web.pojo.Material;
import xyz.innky.graduationproject.web.service.MaterialService;
import xyz.innky.graduationproject.web.vo.Result;

import java.io.File;
import java.io.IOException;
import java.util.Date;

@Controller
@RequestMapping("/teacher/material")
public class TeacherMaterialController {

    @Autowired
    MaterialService materialService;
    @Value("${static.path}")
    String fileRootPath;
    @Value("${server.address}")
    String serverAddress;
    @Value("${server.port}")
    String serverPort;


    @PostMapping("/")
    public Result addMaterial(Material material){
        material.setMaterialPath("http://"+serverAddress+":"+serverPort+"/"+material.getMaterialName());
        material.setUpdateTime(new Date(System.currentTimeMillis()));
        return ResultUtil.returnResultByCondition(materialService.save(material),"添加材料");
    }
    



    @PostMapping("/file")
    public Result addFile(MultipartFile multipartFile){
        File file = new File(fileRootPath+multipartFile.getOriginalFilename());
        try {
            multipartFile.transferTo(file);
            return Result.ok("上传成功");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Result.err("上传失败");
    }


}
