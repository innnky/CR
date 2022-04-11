package xyz.innky.graduationproject.web.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import xyz.innky.graduationproject.web.pojo.ClassInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import xyz.innky.graduationproject.web.pojo.Student;
import xyz.innky.graduationproject.web.vo.ClassInfoVo;

import java.util.List;

/**
* @author xingyijin
* @description 针对表【class_info】的数据库操作Service
* @createDate 2022-04-06 15:13:05
*/
public interface ClassInfoService extends IService<ClassInfo> {

    IPage<ClassInfoVo> getAllClass(Integer page, Integer pageSize, String college, String major, String grade, String className);

    ClassInfo selectByClassId(Integer classId);

    IPage<ClassInfoVo> getAllClassWithoutTotal(int i, int i1, String college, String major, String grade, String className);

    List<Student> getStudentsByClass(Integer id);
}
