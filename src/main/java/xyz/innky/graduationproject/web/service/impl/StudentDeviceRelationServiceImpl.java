package xyz.innky.graduationproject.web.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import xyz.innky.graduationproject.web.pojo.StudentDeviceRelation;
import xyz.innky.graduationproject.web.service.StudentDeviceRelationService;
import xyz.innky.graduationproject.web.mapper.StudentDeviceRelationMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

/**
* @author xingyijin
* @description 针对表【student_device_relation】的数据库操作Service实现
* @createDate 2022-04-06 15:13:05
*/
@Service
public class StudentDeviceRelationServiceImpl extends ServiceImpl<StudentDeviceRelationMapper, StudentDeviceRelation>
    implements StudentDeviceRelationService{

    @Override
    public List<StudentDeviceRelation> getAllDevicesByStudentId(Integer studentId) {
        return getBaseMapper().getAllByStudentId(studentId);
    }

    @Override
    public boolean deleteReservation(Integer deviceId, Integer studentId, Integer exerciseId) {
        return getBaseMapper().deleteByDeviceIdAndStudentIdAndExerciseId(deviceId, studentId, exerciseId) > 0;
    }

    @Override
    public StudentDeviceRelation getReservation(Integer exerciseId, Integer studentId) {
        return getBaseMapper().getOneByExerciseIdAndStudentId(exerciseId, studentId);
    }
}




