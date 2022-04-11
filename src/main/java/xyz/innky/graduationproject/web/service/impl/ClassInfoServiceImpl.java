package xyz.innky.graduationproject.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import xyz.innky.graduationproject.common.utils.CopyUtil;
import xyz.innky.graduationproject.web.mapper.StudentMapper;
import xyz.innky.graduationproject.web.pojo.ClassInfo;
import xyz.innky.graduationproject.web.pojo.Student;
import xyz.innky.graduationproject.web.service.ClassInfoService;
import xyz.innky.graduationproject.web.mapper.ClassInfoMapper;
import org.springframework.stereotype.Service;
import xyz.innky.graduationproject.web.service.StudentService;
import xyz.innky.graduationproject.web.service.TeacherService;
import xyz.innky.graduationproject.web.vo.ClassInfoVo;

import java.util.List;

/**
* @author xingyijin
* @description 针对表【class_info】的数据库操作Service实现
* @createDate 2022-04-06 15:13:05
*/
@Service
public class ClassInfoServiceImpl extends ServiceImpl<ClassInfoMapper, ClassInfo>
    implements ClassInfoService{

    @Autowired
    StudentService studentService;
    @Autowired
    TeacherService teacherService;

    @Override
    public IPage<ClassInfoVo> getAllClass(Integer page, Integer pageSize, String college, String major, String grade, String className) {
        IPage<ClassInfoVo> infoPage = getAllClassWithoutTotal(page, pageSize, college, major, grade, className);
        for (ClassInfoVo record : infoPage.getRecords()) {
            Long aLong = studentService.selectCountByClassId(record.getClassId());
            record.setStudentCount(aLong.intValue());
            record.setTeacherName(teacherService.getById(record.getHeadTeacherId()).getTeacherName());
        }
        return infoPage;
    }



    @Override
    public ClassInfo selectByClassId(Integer classId) {
        return getBaseMapper().selectByClassId(classId);
    }

    @Override
    public IPage<ClassInfoVo> getAllClassWithoutTotal(int i, int i1, String college, String major, String grade, String className) {
        Page<ClassInfo> infoPage = new Page<>(i,i1);
//        LambdaQueryWrapper<ClassInfoVo> infoVoQueryWrapper = new LambdaQueryWrapper<>();
//        infoVoQueryWrapper.like(!ObjectUtils.isEmpty(college),ClassInfo::getCollege, college);
//        infoVoQueryWrapper.like(!ObjectUtils.isEmpty(major),ClassInfo::getMajor, major);
//        infoVoQueryWrapper.eq(!ObjectUtils.isEmpty(grade),ClassInfo::getGrade, grade);
//        infoVoQueryWrapper.like(!ObjectUtils.isEmpty(className),ClassInfo::getClassName, className);
        LambdaQueryWrapper<ClassInfo> infoVoQueryWrapper = Wrappers.lambdaQuery(ClassInfo.class)
                .like(!ObjectUtils.isEmpty(college),ClassInfo::getCollege, college)
                .like(!ObjectUtils.isEmpty(major),ClassInfo::getMajor, major)
                .eq(!ObjectUtils.isEmpty(grade),ClassInfo::getGrade, grade)
                .like(!ObjectUtils.isEmpty(className),ClassInfo::getClassName, className);
//        getBaseMapper().selectPageVo(infoPage, infoVoQueryWrapper);
        Page<ClassInfo> res = getBaseMapper().selectPage(infoPage, infoVoQueryWrapper);
        return res.convert(e -> CopyUtil.copy(e, ClassInfoVo.class));
    }

    @Override
    public List<Student> getStudentsByClass(Integer id) {
        return studentService.getStudentsByClass(id);
    }
}




