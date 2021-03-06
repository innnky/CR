package xyz.innky.graduationproject.web.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import xyz.innky.graduationproject.web.pojo.Device;
import com.baomidou.mybatisplus.extension.service.IService;
import xyz.innky.graduationproject.web.pojo.StudentDeviceRelation;
import xyz.innky.graduationproject.web.vo.DeviceVo;

import java.util.List;

/**
* @author xingyijin
* @description 针对表【device】的数据库操作Service
* @createDate 2022-04-06 15:13:05
*/
public interface DeviceService extends IService<Device> {

    Page<DeviceVo> getAllDeviceByConditionAndPage(String page, String pageSize, String deviceName, String imageType, String status, Integer deviceId);

    boolean addDevice(Device device);

    boolean removeDevice(String id);

    void stopDevice(String uuid);

    void startDevice(String uuid);

    List<Device> getUnusedDevice();

    List<DeviceVo> getAllDevicesByScene(Integer sceneId);

    boolean addReservation(StudentDeviceRelation reservation);

    List<StudentDeviceRelation> getAllDevicesByStudentId(Integer studentId);

    String getVncUrl(Integer deviceId);

    boolean deleteReservation(Integer deviceId, Integer studentId, Integer exerciseId);

    StudentDeviceRelation getReservation(Integer exerciseId, Integer studentId);

    Object getStudentVncUrl(Integer exerciseId);
}
