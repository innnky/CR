package xyz.innky.graduationproject.web.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import xyz.innky.graduationproject.web.pojo.CourseTime;
import xyz.innky.graduationproject.web.service.CourseTimeService;
import xyz.innky.graduationproject.web.mapper.CourseTimeMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author xingyijin
* @description 针对表【course_time】的数据库操作Service实现
* @createDate 2022-04-12 11:24:45
*/
@Service
public class CourseTimeServiceImpl extends ServiceImpl<CourseTimeMapper, CourseTime>
    implements CourseTimeService{

    @Override
    public Page<CourseTime> getAllByCourseId(Page<CourseTime> courseTimePage, Integer id) {
        return getBaseMapper().getAllByCourseId(courseTimePage,id);
    }

    @Override
    public List<CourseTime> getBySCid(Integer scid) {
        return getBaseMapper().getAllByCourseId(new Page<>(1,-1), scid).getRecords();
    }
}




