package xyz.innky.graduationproject.web.service;

import xyz.innky.graduationproject.web.pojo.StudentDeviceRelation;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author xingyijin
* @description 针对表【student_device_relation】的数据库操作Service
* @createDate 2022-04-06 15:13:05
*/
public interface StudentDeviceRelationService extends IService<StudentDeviceRelation> {

    List<StudentDeviceRelation> getAllDevicesByStudentId(Integer studentId);

    public boolean deleteReservation(Integer deviceId, Integer studentId, Integer exerciseId);

    StudentDeviceRelation getReservation(Integer exerciseId, Integer studentId);

    boolean addReservation(StudentDeviceRelation reservation);
}
