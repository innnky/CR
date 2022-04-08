package xyz.innky.graduationproject.web.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import xyz.innky.graduationproject.web.pojo.Device;
import com.baomidou.mybatisplus.extension.service.IService;
import xyz.innky.graduationproject.web.vo.DeviceVo;

/**
* @author xingyijin
* @description 针对表【device】的数据库操作Service
* @createDate 2022-04-06 15:13:05
*/
public interface DeviceService extends IService<Device> {

    Page<DeviceVo> getAllDeviceByConditionAndPage(String page, String pageSize, String deviceName, String imageType, String status, Integer deviceId);
}