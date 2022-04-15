package xyz.innky.graduationproject.web.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import xyz.innky.graduationproject.web.pojo.ClassInfo;
import xyz.innky.graduationproject.web.pojo.Course;
import xyz.innky.graduationproject.web.pojo.SCourse;
import xyz.innky.graduationproject.web.service.SCourseClassRelationService;
import xyz.innky.graduationproject.web.service.SCourseService;
import xyz.innky.graduationproject.web.mapper.SCourseMapper;
import org.springframework.stereotype.Service;
import xyz.innky.graduationproject.web.vo.SCourseVo;

import java.util.List;
import java.util.stream.Collectors;

/**
* @author xingyijin
* @description 针对表【s_course】的数据库操作Service实现
* @createDate 2022-04-12 08:38:01
*/
@Service
@Transactional
public class SCourseServiceImpl extends ServiceImpl<SCourseMapper, SCourse>
    implements SCourseService{
    @Autowired
    private SCourseClassRelationService sCourseClassRelationService;


    @Override
    public boolean addSCourse(SCourseVo sCourse) {
        //添加scourse表
        if (getBaseMapper().insertSelective(sCourse)==1) {
            //添加scourse班级关联表
            List<Integer> classIds = sCourse.getClassIds();
            sCourseClassRelationService.addSCourseClassRelation(sCourse.getSCourseId(), classIds);
            return true;
        }
        return false;
    }

    @Override

    public boolean removeSCourse(Integer id) {
        //删除scourse表
        if (getBaseMapper().deleteById(id)==1) {
            getBaseMapper().deleteBySCourseId(id);
            //删除scourse班级关联表
            sCourseClassRelationService.removeSCourseClassRelation(id);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateSCourse(SCourseVo sCourse) {
        if (getBaseMapper().updateSelective(sCourse)==1) {
            sCourseClassRelationService.removeSCourseClassRelation(sCourse.getSCourseId());
            List<Integer> classIds = sCourse.getClassIds();
            sCourseClassRelationService.addSCourseClassRelation(sCourse.getSCourseId(), classIds);
            return true;
        }
        return false;
    }

    @Override
    public Page<SCourseVo> getAllSCourse(Integer page, Integer pageSize, String className, String courseName, String teacherName) {
        Page<SCourseVo> page1 = new Page<>(page, pageSize);
        List<SCourseVo> allSCourse = getBaseMapper().getAllSCourse(className, courseName, teacherName);
        page1.setTotal(allSCourse.size());
        allSCourse = allSCourse.stream().skip((page-1)*pageSize).limit(pageSize).collect(Collectors.toList());
        page1.setRecords(allSCourse);
        return page1;
    }

    @Override
    public List<ClassInfo> getCourseClass(Integer id) {
        return sCourseClassRelationService.getCourseClass(id);
    }

    @Override
    public Course getCourseBySid(Integer sCourseId) {
        return getBaseMapper().getCourseBySid(sCourseId);
    }

}




