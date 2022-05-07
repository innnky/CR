package xyz.innky.graduationproject.web.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;
import xyz.innky.graduationproject.web.pojo.ClassStudentRelation;
import xyz.innky.graduationproject.web.pojo.Student;
import xyz.innky.graduationproject.web.service.ClassStudentRelationService;
import xyz.innky.graduationproject.web.mapper.ClassStudentRelationMapper;
import org.springframework.stereotype.Service;
import xyz.innky.graduationproject.web.vo.ClassStudentVo;
import xyz.innky.graduationproject.web.vo.StudentVo;

import java.util.Date;
import java.util.List;

/**
* @author xingyijin
* @description 针对表【class_student_relation】的数据库操作Service实现
* @createDate 2022-04-12 20:38:43
*/
@Service
@Transactional
public class ClassStudentRelationServiceImpl extends ServiceImpl<ClassStudentRelationMapper, ClassStudentRelation>
    implements ClassStudentRelationService{

    @Override
    public List<ClassStudentVo> getStudentsByClass(Integer id) {
        return getBaseMapper().getAllByClassId(id);
    }

    @Override
    public List<ClassStudentVo> getHistoryClass(Integer sid) {
        return getBaseMapper().getAllByStudentId(sid);
    }

    @Override
    public boolean addStudent(Student student) {

//        if(getBaseMapper().updateEndTimeByStudentId(new Date(System.currentTimeMillis()), student.getStudentId())==1){
//            ClassStudentRelation relation = new ClassStudentRelation();
//            relation.setClassId(student.getClassId());
//            relation.setStudentId(student.getStudentId());
//            relation.setStartTime(new Date(System.currentTimeMillis()));
//            if(getBaseMapper().insertSelective(relation)==1){
//                return true;
//            }
//        }
        ClassStudentRelation relation = new ClassStudentRelation();
        relation.setClassId(student.getClassId());
        relation.setStudentId(student.getStudentId());
        relation.setStartTime(new Date(System.currentTimeMillis()));
        if(getBaseMapper().insertSelective(relation)==1){
            return true;
        }
        throw new RuntimeException();
    }

    @Override
    public boolean changeStudentClass(Student student) {
        if(getBaseMapper().updateEndTimeByStudentId(new Date(System.currentTimeMillis()), student.getStudentId())==1){
            ClassStudentRelation relation = new ClassStudentRelation();
            relation.setClassId(student.getClassId());
            relation.setStudentId(student.getStudentId());
            relation.setStartTime(new Date(System.currentTimeMillis()));
            if(getBaseMapper().insertSelective(relation)==1){
                return true;
            }
        }
        throw new RuntimeException();
    }
}




