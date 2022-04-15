package xyz.innky.graduationproject.web.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import xyz.innky.graduationproject.web.pojo.Material;
import xyz.innky.graduationproject.web.service.CourseService;
import xyz.innky.graduationproject.web.service.MaterialService;
import xyz.innky.graduationproject.web.mapper.MaterialMapper;
import org.springframework.stereotype.Service;
import xyz.innky.graduationproject.web.vo.StuCourseVo;

import java.util.List;
import java.util.stream.Collectors;

/**
* @author xingyijin
* @description 针对表【material】的数据库操作Service实现
* @createDate 2022-04-06 15:13:05
*/
@Service
public class MaterialServiceImpl extends ServiceImpl<MaterialMapper, Material>
    implements MaterialService{
    @Autowired
    CourseService courseService;

    @Override
    public List<Material> getMaterialBySCid(Integer scid) {
        return getBaseMapper().getAllBySCourseId(scid);
    }

    @Override
    public List<Material> getMaterial(Integer studentId) {
        List<StuCourseVo> courses = courseService.getCourses(studentId, null);
//        return getBaseMapper().getAllBySCourseIdIn(courses.stream().map(StuCourseVo::getSCourseId).collect(Collectors.toList()));
        return getBaseMapper().getAllBySCourseIdIn(courses.stream().map(StuCourseVo::getSCourseId).collect(Collectors.toList()));
    }
}




