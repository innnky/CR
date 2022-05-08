package xyz.innky.graduationproject.web.service;

import xyz.innky.graduationproject.web.pojo.ClassInfo;
import xyz.innky.graduationproject.web.pojo.SCourseClassRelation;
import com.baomidou.mybatisplus.extension.service.IService;
import xyz.innky.graduationproject.web.vo.SCourseVo;

import java.util.List;

/**
* @author xingyijin
* @description 针对表【s_course_class_relation】的数据库操作Service
* @createDate 2022-04-12 08:38:01
*/
public interface SCourseClassRelationService extends IService<SCourseClassRelation> {

    void addSCourseClassRelation(Integer sCourseId, List<Integer> classInfos);

    void removeSCourseClassRelation(Integer id);

    List<ClassInfo> getCourseClass(Integer id);

    List<SCourseClassRelation> getByClassId(Integer classId);

    boolean hasRepeatSCourses(SCourseVo sCourse);
}
