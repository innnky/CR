package xyz.innky.graduationproject.web.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import xyz.innky.graduationproject.web.pojo.CourseTime;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author xingyijin
* @description 针对表【course_time】的数据库操作Service
* @createDate 2022-04-12 11:24:45
*/
public interface CourseTimeService extends IService<CourseTime> {

    Page<CourseTime> getAllByCourseId(Page<CourseTime> courseTimePage, Integer id);

    List<CourseTime> getBySCid(Integer scid);
}
