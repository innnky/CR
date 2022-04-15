package xyz.innky.graduationproject.web.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import xyz.innky.graduationproject.web.pojo.Exercise;
import xyz.innky.graduationproject.web.service.CourseService;
import xyz.innky.graduationproject.web.service.ExerciseService;
import xyz.innky.graduationproject.web.mapper.ExerciseMapper;
import org.springframework.stereotype.Service;
import xyz.innky.graduationproject.web.vo.StuCourseVo;

import java.util.List;
import java.util.stream.Collectors;

/**
* @author xingyijin
* @description 针对表【exercise】的数据库操作Service实现
* @createDate 2022-04-06 15:13:05
*/
@Service
public class ExerciseServiceImpl extends ServiceImpl<ExerciseMapper, Exercise>
    implements ExerciseService{

    @Autowired
    CourseService courseService;

    @Override
    public List<Exercise> getExerciseBySCid(Integer scid) {
        return getBaseMapper().getAllBySCourseId(scid);
    }

    @Override
    public List<Exercise> getExercise(Integer studentId) {
        List<StuCourseVo> courses = courseService.getCourses(studentId, null);
//        courses.stream().map(StuCourseVo::getSCourseId).collect(Collectors.toList())
        return getBaseMapper().getAllBySCourseIdIn(courses.stream().map(StuCourseVo::getSCourseId).collect(Collectors.toList()));
    }
}




