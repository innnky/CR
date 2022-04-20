package xyz.innky.graduationproject.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import xyz.innky.graduationproject.common.utils.AccountUtil;
import xyz.innky.graduationproject.common.utils.TimePeriod;
import xyz.innky.graduationproject.openstack.api.DeviceApi;
import xyz.innky.graduationproject.web.pojo.Device;
import xyz.innky.graduationproject.web.pojo.Image;
import xyz.innky.graduationproject.web.pojo.StudentDeviceRelation;
import xyz.innky.graduationproject.web.service.DeviceService;
import xyz.innky.graduationproject.web.mapper.DeviceMapper;
import org.springframework.stereotype.Service;
import xyz.innky.graduationproject.web.service.ImageService;
import xyz.innky.graduationproject.web.service.StudentDeviceRelationService;
import xyz.innky.graduationproject.web.vo.DeviceVo;
import xyz.innky.graduationproject.web.vo.StudentDeviceReservation;

import java.util.Date;
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
    @Autowired
    DeviceApi deviceApi;
    @Autowired
    StudentDeviceRelationService studentDeviceRelationService;

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
        deviceLambdaQueryWrapper.in(Device::getImageId, imageIds);
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

    @Override
    public boolean addDevice(Device device) {
        String device1 = deviceApi.createDevice(imageService.getById(device.getImageId()).getUuid(), device.getDeviceName());
        device.setUuid(device1);
        return save(device);
    }

    @Override
    public boolean removeDevice(String id) {
        Device device = getById(id);
        deviceApi.deleteDevice(device.getUuid());
        return removeById(id);
    }

    @Override
    public void stopDevice(String uuid) {
        getBaseMapper().updateStatusByUuid(0, uuid);
    }

    @Override
    public void startDevice(String uuid) {
        getBaseMapper().updateStatusByUuid(1, uuid);
    }

    @Override
    public List<Device> getUnusedDevice() {
        return getBaseMapper().getUnusedDevice();
    }

    @Override
    public List<DeviceVo> getAllDevicesByScene(Integer sceneId) {
        return getBaseMapper().getAllDevicesByScene(sceneId);
    }

    @Override
    public boolean addReservation(Integer deviceId, StudentDeviceReservation reservation) {
        StudentDeviceRelation relation = new StudentDeviceRelation();
        relation.setDeviceId(deviceId);
        relation.setStudentId(AccountUtil.getStudentId());
        TimePeriod period = TimePeriod.getInstance(reservation.getSequence());
        Date start = new Date(reservation.getDate().getTime()+ period.getStart());
        Date end = new Date(reservation.getDate().getTime()+ period.getEnd());
        relation.setStartTime(start);
        relation.setEndTime(end);
        return studentDeviceRelationService.save(relation);
    }

    @Override
    public List<StudentDeviceReservation> getAllDevicesByStudentId(Integer studentId) {
        return studentDeviceRelationService.getAllDevicesByStudentId(studentId);
    }
}




