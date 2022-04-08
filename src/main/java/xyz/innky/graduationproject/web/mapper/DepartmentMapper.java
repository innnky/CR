package xyz.innky.graduationproject.web.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import xyz.innky.graduationproject.web.pojo.Department;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import xyz.innky.graduationproject.web.vo.DepartmentVo;

/**
* @author xingyijin
* @description 针对表【department】的数据库操作Mapper
* @createDate 2022-04-06 15:13:05
* @Entity xyz.innky.graduationproject.web.pojo.Department
*/
public interface DepartmentMapper extends BaseMapper<Department> {

    Page<DepartmentVo> selectPageVo(@Param("departmentVoPage") Page<DepartmentVo> departmentVoPage, @Param("ew") Wrapper<DepartmentVo> ew);
}




