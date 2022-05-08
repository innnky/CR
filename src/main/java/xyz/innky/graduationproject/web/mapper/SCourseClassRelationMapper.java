package xyz.innky.graduationproject.web.mapper;

import org.apache.ibatis.annotations.Param;
import xyz.innky.graduationproject.web.pojo.ClassInfo;
import xyz.innky.graduationproject.web.pojo.SCourseClassRelation;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import xyz.innky.graduationproject.web.vo.SCVO;

import java.util.List;

/**
* @author xingyijin
* @description 针对表【s_course_class_relation】的数据库操作Mapper
* @createDate 2022-04-12 08:38:01
* @Entity xyz.innky.graduationproject.web.pojo.SCourseClassRelation
*/
public interface SCourseClassRelationMapper extends BaseMapper<SCourseClassRelation> {

    void addSCourseClassRelation(@Param("sCourseId") Integer sCourseId, @Param("classInfos") List<Integer> classInfos);

    int deleteBySCourseId(@Param("sCourseId") Integer sCourseId);

    List<ClassInfo> getAllBySCourseId(@Param("sCourseId") Integer sCourseId);

    List<SCourseClassRelation> getAllByClassId(@Param("classId") Integer classId);

    List<SCVO> getSCVO();
}




