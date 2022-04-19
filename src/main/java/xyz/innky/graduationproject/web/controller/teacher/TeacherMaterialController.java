package xyz.innky.graduationproject.web.controller.teacher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import xyz.innky.graduationproject.common.utils.ResultUtil;
import xyz.innky.graduationproject.web.pojo.Material;
import xyz.innky.graduationproject.web.service.MaterialService;
import xyz.innky.graduationproject.web.vo.Result;

import java.io.File;
import java.io.IOException;
import java.util.Date;

@RestController
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


//    @PostMapping("/file")
//    public Result addExerciseFile(MultipartFile file) {
////        Integer teacherId = AccountUtil.getTeacherId();
//        String  filePath = staticPath + file.getOriginalFilename();
//        try {
//            file.transferTo(new java.io.File(filePath));
//            return Result.ok("");
//        } catch (IOException e) {
//            e.printStackTrace();
//            return Result.err("上传失败");
//        }
//    }



    @PostMapping("/file/{scid}")
    public Result addFile(MultipartFile file, @PathVariable("scid") Integer scid) {
        File file1 = new File(fileRootPath+"material/"+file.getOriginalFilename());
        try {
            file.transferTo(file1);
            Material material = new Material();
            material.setMaterialName(file.getOriginalFilename());
            material.setSCourseId(scid);
            material.setMaterialPath("http://"+serverAddress+":"+serverPort+"/material/"+file.getOriginalFilename());
            material.setUpdateTime(new Date(System.currentTimeMillis()));
            return ResultUtil.returnResultByCondition(materialService.save(material),"添加材料");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Result.err("上传失败");
    }

    @DeleteMapping("/{mid}")
    public Result deleteMaterial(@PathVariable("mid") Integer mid){
        return ResultUtil.returnResultByCondition(materialService.removeById(mid),"删除材料");
    }


}
