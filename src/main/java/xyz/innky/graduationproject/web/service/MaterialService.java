package xyz.innky.graduationproject.web.service;

import xyz.innky.graduationproject.web.pojo.Material;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author xingyijin
* @description 针对表【material】的数据库操作Service
* @createDate 2022-04-06 15:13:05
*/
public interface MaterialService extends IService<Material> {

    List<Material> getMaterialBySCid(Integer scid);

    List<Material> getMaterial(Integer studentId);
}
