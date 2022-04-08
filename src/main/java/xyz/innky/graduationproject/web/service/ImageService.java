package xyz.innky.graduationproject.web.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import xyz.innky.graduationproject.web.pojo.Image;
import com.baomidou.mybatisplus.extension.service.IService;

import java.io.Serializable;

/**
* @author xingyijin
* @description 针对表【image】的数据库操作Service
* @createDate 2022-04-06 15:13:05
*/
public interface ImageService extends IService<Image> {

    Page<Image> getAllImagesOnConditionAndPage(Integer page, Integer pageSize, String imageName, String imageType, Integer imageId);

    @Override
    default boolean removeById(Serializable id) {
        return IService.super.removeById(id);
    }
}
