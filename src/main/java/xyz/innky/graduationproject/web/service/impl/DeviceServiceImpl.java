package xyz.innky.graduationproject.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import xyz.innky.graduationproject.web.pojo.Device;
import xyz.innky.graduationproject.web.pojo.Image;
import xyz.innky.graduationproject.web.service.DeviceService;
import xyz.innky.graduationproject.web.mapper.DeviceMapper;
import org.springframework.stereotype.Service;
import xyz.innky.graduationproject.web.service.ImageService;
import xyz.innky.graduationproject.web.vo.DeviceVo;

import java.util.List;

/**
* @author xingyijin
* @description 针对表【device】的数据库操作Service实现
* @createDate 2022-04-06 15:13:05
*/
@Service
public class DeviceServiceImpl extends ServiceImpl<DeviceMapper, Device>
    implements DeviceService{

    @Autowired
    ImageService imageService;


    @Override
    public Page<DeviceVo> getAllDeviceByConditionAndPage(String page, String pageSize, String deviceName, String imageType, String status, Integer deviceId) {
        Page<DeviceVo> deviceVoPage = new Page<>(Integer.parseInt(page), Integer.parseInt(pageSize));
        Page<Image> allImagesOnConditionAndPage = imageService.getAllImagesOnConditionAndPage(1, -1, null, imageType, null);
        List<Image> records = allImagesOnConditionAndPage.getRecords();
        if (records.size() == 0) {
            return deviceVoPage;
        }
        List<Integer> imageIds = records.stream().map(Image::getImageId).collect(java.util.stream.Collectors.toList());
        LambdaQueryWrapper<Device> deviceLambdaQueryWrapper = new LambdaQueryWrapper<>();
        deviceLambdaQueryWrapper.in(Device::getDeviceId, imageIds);
        deviceLambdaQueryWrapper.eq(!ObjectUtils.isEmpty(deviceId), Device::getDeviceId, deviceId);
        deviceLambdaQueryWrapper.like(!ObjectUtils.isEmpty(deviceName), Device::getDeviceName, deviceName);
        deviceLambdaQueryWrapper.eq(!ObjectUtils.isEmpty(status), Device::getStatus, status);
        getBaseMapper().selectPageVo(deviceVoPage, deviceLambdaQueryWrapper);
        //遍历deviceVoPage
        for (DeviceVo deviceVo : deviceVoPage.getRecords()) {
            deviceVo.setImage(imageService.getById(deviceVo.getImageId()));
        }
        return deviceVoPage;
    }
}




