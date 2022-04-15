package xyz.innky.graduationproject.web.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import xyz.innky.graduationproject.web.pojo.ClassInfo;
import xyz.innky.graduationproject.web.pojo.SCourseClassRelation;
import xyz.innky.graduationproject.web.service.SCourseClassRelationService;
import xyz.innky.graduationproject.web.mapper.SCourseClassRelationMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author xingyijin
* @description 针对表【s_course_class_relation】的数据库操作Service实现
* @createDate 2022-04-12 08:38:01
*/
@Service
public class SCourseClassRelationServiceImpl extends ServiceImpl<SCourseClassRelationMapper, SCourseClassRelation>
    implements SCourseClassRelationService{

    @Override
    public void addSCourseClassRelation(Integer sCourseId, List<Integer> classInfos) {
        getBaseMapper().addSCourseClassRelation(sCourseId, classInfos);
    }

    @Override
    public void removeSCourseClassRelation(Integer id) {
        getBaseMapper().deleteBySCourseId(id);
    }

    @Override
    public List<ClassInfo> getCourseClass(Integer id) {
        return getBaseMapper().getAllBySCourseId(id);
    }

    @Override
    public List<SCourseClassRelation> getByClassId(Integer classId) {
        return getBaseMapper().getAllByClassId(classId);
    }
}




