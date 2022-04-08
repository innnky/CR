package xyz.innky.graduationproject.web.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import xyz.innky.graduationproject.web.pojo.Department;
import com.baomidou.mybatisplus.extension.service.IService;
import xyz.innky.graduationproject.web.vo.DepartmentVo;

/**
* @author xingyijin
* @description 针对表【department】的数据库操作Service
* @createDate 2022-04-06 15:13:05
*/
public interface DepartmentService extends IService<Department> {

    Page<DepartmentVo> getAllDepartment(Integer page, Integer pageSize, Integer departmentId, String departmentName);
}
