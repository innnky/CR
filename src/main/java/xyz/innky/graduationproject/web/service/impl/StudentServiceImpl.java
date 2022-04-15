package xyz.innky.graduationproject.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import xyz.innky.graduationproject.web.mapper.ClassInfoMapper;
import xyz.innky.graduationproject.web.mapper.DutyMapper;
import xyz.innky.graduationproject.web.pojo.ClassInfo;
import xyz.innky.graduationproject.web.pojo.Duty;
import xyz.innky.graduationproject.web.pojo.Student;
import xyz.innky.graduationproject.web.service.ClassInfoService;
import xyz.innky.graduationproject.web.service.ClassStudentRelationService;
import xyz.innky.graduationproject.web.service.DutyService;
import xyz.innky.graduationproject.web.service.StudentService;
import xyz.innky.graduationproject.web.mapper.StudentMapper;
import org.springframework.stereotype.Service;
import xyz.innky.graduationproject.web.vo.ClassInfoVo;
import xyz.innky.graduationproject.web.vo.ClassStudentVo;
import xyz.innky.graduationproject.web.vo.StudentVo;

import java.util.ArrayList;
import java.util.List;

/**
* @author xingyijin
* @description 针对表【student(学生信息)】的数据库操作Service实现
* @createDate 2022-04-06 15:13:05
*/
@Service
@Transactional
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student>
    implements StudentService{
    @Autowired
    DutyService dutyService;
    @Autowired
    ClassInfoService classInfoService;
    @Autowired
    ClassStudentRelationService classStudentRelationService;
    @Override
    public Page<StudentVo> getAllStudents(Integer page, Integer pageSize, String studentName, Integer studentId, String college, String grade, String major, String className, String gender) {

        Page<StudentVo> studentVoPage = new Page<>(page, pageSize);
        List<ClassInfoVo> classInfos = classInfoService.getAllClassWithoutTotal(1, -1, college, major, grade, className).getRecords();
        if (classInfos.size() == 0){
            return studentVoPage;
        }
        List<Integer> classes = new ArrayList<>();
        for (ClassInfo classInfo : classInfos) {
            classes.add(classInfo.getClassId());
        }
        LambdaQueryWrapper<StudentVo> studentVoLambdaQueryWrapper = new LambdaQueryWrapper<>();
        studentVoLambdaQueryWrapper.like(!ObjectUtils.isEmpty(studentName), StudentVo::getStudentName, studentName);
        studentVoLambdaQueryWrapper.eq(!ObjectUtils.isEmpty(studentId), StudentVo::getStudentId, studentId);
        studentVoLambdaQueryWrapper.in(StudentVo::getClassId, classes);
        studentVoLambdaQueryWrapper.eq(!ObjectUtils.isEmpty(gender), StudentVo::getGender, gender);
        getBaseMapper().selectPageVo(studentVoPage, studentVoLambdaQueryWrapper);
        for (StudentVo record : studentVoPage.getRecords()) {
            ClassInfo classInfos1 = classInfoService.selectByClassId(record.getClassId());
            record.setClassInfo(classInfos1);
            List<Duty> duties = dutyService.searchAllByStudentId(record.getStudentId());
            record.setDuties(duties);
        }
        return studentVoPage;
    }

    @Override
    public Long selectCountByClassId(Integer classId) {
        return getBaseMapper().selectCountByClassId(classId);
    }

    @Override
    public List<Student> getStudentsByClass(Integer id) {
        return getBaseMapper().selectByClassId(id);
    }

    @Override
    public List<Student> getAllStudentsByName(String studentName) {
        return getBaseMapper().selectByStudentName(studentName);
    }

    @Override
    public List<ClassStudentVo> getHistoryClass(Integer id) {
        return classStudentRelationService.getHistoryClass(id);
    }

    @Override
    public boolean addStudent(Student student) {
        if (getBaseMapper().insertSelective(student)==1){
            if (classStudentRelationService.addStudent(student)){
                return true;
            }
        }
        throw new RuntimeException();
    }

    @Override
    public boolean updateStudentClass(Student student) {
        if (getBaseMapper().updateClassIdByStudentId(student.getClassId(),student.getStudentId())==1){
            if (classStudentRelationService.changeStudentClass(student)){
                return true;
            }
        }
        throw new RuntimeException();
    }

}




