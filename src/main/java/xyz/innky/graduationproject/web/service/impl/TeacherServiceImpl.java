package xyz.innky.graduationproject.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import xyz.innky.graduationproject.web.pojo.Department;
import xyz.innky.graduationproject.web.pojo.Teacher;
import xyz.innky.graduationproject.web.service.DepartmentService;
import xyz.innky.graduationproject.web.service.TeacherService;
import xyz.innky.graduationproject.web.mapper.TeacherMapper;
import org.springframework.stereotype.Service;
import xyz.innky.graduationproject.web.vo.TeacherVo;

import java.util.List;

/**
* @author xingyijin
* @description 针对表【teacher(教师信息表)】的数据库操作Service实现
* @createDate 2022-04-06 15:13:05
*/
@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher>
    implements TeacherService{
    @Autowired
    DepartmentService departmentService;

    @Override
    public Page<TeacherVo> getAllTeacher(Integer page, Integer pageSize, String teacherName, Integer teacherId, String gender, Integer departmentId) {
        Page<TeacherVo> teacherVoPage = new Page<>(page,pageSize);
        LambdaQueryWrapper<TeacherVo> wrapper = new LambdaQueryWrapper<>();
        if (!ObjectUtils.isEmpty(teacherName)){
            wrapper.like(Teacher::getTeacherName,teacherName);
        }
        if (!ObjectUtils.isEmpty(teacherId)){
            wrapper.eq(Teacher::getTeacherId,teacherId);
        }
        if (!ObjectUtils.isEmpty(gender)){
            wrapper.eq(Teacher::getGender, gender);
        }

        getBaseMapper().selectPageVo(teacherVoPage,wrapper, departmentId);
        return teacherVoPage;
    }

    @Override
    public List<Teacher> searchAllByDepartmentId(Integer departmentId) {
        return getBaseMapper().searchAllByDepartmentId(departmentId);
    }

    @Override
    public Integer searchAllCountByDepartmentId(Integer departmentId) {
        return baseMapper.searchAllCountByDepartmentId(departmentId);
    }

    @Override
    public List<Teacher> getAllTeachersByName(String teacherName) {
        return getBaseMapper().getAllByTeacherName(teacherName);
    }

    @Override
    public Teacher getByTeacherNumberAndTeacherName(String teacherNumber, String teacherName) {
        return getBaseMapper().getOneByTeacherNameAndTeacherNumber(teacherName, teacherNumber.toString());
    }

    @Override
    public Boolean addTeacher(Teacher teacher) {
        Department dep = departmentService.getById(teacher.getDepartmentId());
        String departmentNumber = dep.getDepartmentNumber();
        String maxNumber = getBaseMapper().getMaxTeacherNumberByDepartmentId(teacher.getDepartmentId());
        if (maxNumber == null){
            teacher.setTeacherNumber(departmentNumber + "001");
        }
        else {
            //取maxNumber的后三位数字
            String number = maxNumber.substring(maxNumber.length() - 3);
            Integer maxNumberInt = Integer.parseInt(number);
            maxNumberInt++;
            //格式化为三位数 不足前面补0
            String maxNumberStr = String.format("%03d", maxNumberInt);
            teacher.setTeacherNumber(departmentNumber + maxNumberStr);
        }
        return save(teacher);
    }
}




