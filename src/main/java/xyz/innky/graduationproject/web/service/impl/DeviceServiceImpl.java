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

import java.util.*;

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
//        deviceLambdaQueryWrapper.in(Device::getImageId, imageIds);
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
        if (Objects.equals(device.getType(), "server")) {
            String device1 = deviceApi.createDevice(imageService.getById(device.getImageId()).getUuid(), device.getDeviceName());
            device.setUuid(device1);
        }
        else if (Objects.equals(device.getType(), "router")) {
            String uuid = deviceApi.createRouter(device.getDeviceName());
            device.setUuid(uuid);
        }
        return save(device);
    }

    @Override
    public boolean removeDevice(String id) {
        Device device = getById(id);
        if (Objects.equals(device.getType(), "server")) {
            deviceApi.deleteDevice(device.getUuid());
        }
        else if (Objects.equals(device.getType(), "router")) {
            deviceApi.deleteRouter(device.getUuid());
        }
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
    public boolean addReservation(StudentDeviceRelation reservation) {
        return studentDeviceRelationService.saveOrUpdate(reservation);
    }

    @Override
    public List<StudentDeviceRelation> getAllDevicesByStudentId(Integer studentId) {
        return studentDeviceRelationService.getAllDevicesByStudentId(studentId);
    }

    @Override
    public String getVncUrl(Integer deviceId) {
        Device dev = getById(deviceId);
        return deviceApi.getVncAddress(dev.getUuid());
    }

    @Override
    public boolean deleteReservation(Integer deviceId, Integer studentId, Integer exerciseId) {

        return studentDeviceRelationService.deleteReservation(deviceId, studentId,exerciseId);
    }

    @Override
    public StudentDeviceRelation getReservation(Integer exerciseId, Integer studentId) {
        return studentDeviceRelationService.getReservation(exerciseId, studentId);
    }

    @Override
    public Object getStudentVncUrl(Integer exerciseId) {
        Map<String, Object> map = new HashMap<>();
        Integer studentId = AccountUtil.getStudentId();
        StudentDeviceRelation reservation = getReservation(exerciseId, studentId);
        if (reservation == null) {
            map.put("status","未预约");
            return map;
        }
        long startTick = reservation.getDate().getTime() + TimePeriod.getInstance(reservation.getSequence()).getStart();
        long endTick = reservation.getDate().getTime() + TimePeriod.getInstance(reservation.getSequence()).getEnd();
        long now = System.currentTimeMillis();
        if(now > startTick && now < endTick){
            map.put("status","正在使用");
            map.put("vncUrl", getVncUrl(reservation.getDeviceId()));
        }
        else if(now > endTick){
            map.put("status","已过期");
        }
        else{
            map.put("status","未开始");
        }
        return map;
    }
}




