package xyz.innky.graduationproject.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.util.ObjectUtils;
import xyz.innky.graduationproject.web.pojo.Image;
import xyz.innky.graduationproject.web.service.ImageService;
import xyz.innky.graduationproject.web.mapper.ImageMapper;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
* @author xingyijin
* @description 针对表【image】的数据库操作Service实现
* @createDate 2022-04-06 15:13:05
*/
@Service
public class ImageServiceImpl extends ServiceImpl<ImageMapper, Image>
    implements ImageService{

    @Override
    public Page<Image> getAllImagesOnConditionAndPage(Integer page, Integer pageSize, String imageName, String imageType, Integer imageId) {
        LambdaQueryWrapper<Image> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(!ObjectUtils.isEmpty(imageName), Image::getImageName, imageName);
        queryWrapper.eq(!ObjectUtils.isEmpty(imageType), Image::getImageType, imageType);
        queryWrapper.eq(!ObjectUtils.isEmpty(imageId), Image::getImageId, imageId);
        Page<Image> imagePage = new Page<>(page, pageSize);
        getBaseMapper().selectPage(imagePage, queryWrapper);
        return imagePage;
    }

    @Override
    public boolean removeById(Serializable id) {
        return super.removeById(id);
    }
}




