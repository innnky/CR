package xyz.innky.graduationproject.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import xyz.innky.graduationproject.web.mapper.TeacherMapper;
import xyz.innky.graduationproject.web.pojo.Department;
import xyz.innky.graduationproject.web.service.DepartmentService;
import xyz.innky.graduationproject.web.mapper.DepartmentMapper;
import org.springframework.stereotype.Service;
import xyz.innky.graduationproject.web.service.TeacherService;
import xyz.innky.graduationproject.web.vo.DepartmentVo;

/**
* @author xingyijin
* @description 针对表【department】的数据库操作Service实现
* @createDate 2022-04-06 15:13:05
*/
@Service
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department>
    implements DepartmentService{

    @Autowired
    TeacherService teacherService;

    @Override
    public Page<DepartmentVo> getAllDepartment(Integer page, Integer pageSize, Integer departmentId, String departmentName) {
        Page<DepartmentVo> departmentVoPage =  new Page<>(page, pageSize);
        LambdaQueryWrapper<DepartmentVo> wrapper = new LambdaQueryWrapper<>();
        if (departmentId!=null){
            wrapper.eq(Department::getDepartmentId, departmentId);
        }
        if (!ObjectUtils.isEmpty(departmentName)){
            wrapper.like(Department::getDepartmentName, departmentName);
        }
        getBaseMapper().selectPageVo(departmentVoPage, wrapper);
        for (DepartmentVo record : departmentVoPage.getRecords()) {
            record.setTeachers(teacherService.searchAllByDepartmentId(record.getDepartmentId()));
            record.setCounts(record.getTeachers().size());
        }
        return departmentVoPage;
    }
}




