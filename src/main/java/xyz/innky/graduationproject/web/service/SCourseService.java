package xyz.innky.graduationproject.web.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import xyz.innky.graduationproject.web.pojo.ClassInfo;
import xyz.innky.graduationproject.web.pojo.SCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import xyz.innky.graduationproject.web.vo.SCourseVo;

import java.util.List;

/**
* @author xingyijin
* @description 针对表【s_course】的数据库操作Service
* @createDate 2022-04-12 08:38:01
*/
public interface SCourseService extends IService<SCourse> {

    boolean addSCourse(SCourseVo sCourse);

    boolean removeSCourse(Integer id);

    boolean updateSCourse(SCourseVo sCourse);

    Page<SCourseVo> getAllSCourse(Integer page, Integer pageSize, String className, String courseName, String teacherName);

    List<ClassInfo> getCourseClass(Integer id);
}
