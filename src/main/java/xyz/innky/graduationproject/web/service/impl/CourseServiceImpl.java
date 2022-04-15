package xyz.innky.graduationproject.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import xyz.innky.graduationproject.web.mapper.StudentMapper;
import xyz.innky.graduationproject.web.pojo.*;
//import xyz.innky.graduationproject.web.pojo.TeacherCourseClassRelation;
import xyz.innky.graduationproject.web.service.*;
import xyz.innky.graduationproject.web.mapper.CourseMapper;
import org.springframework.stereotype.Service;
import xyz.innky.graduationproject.web.vo.StuCourseVo;

import java.sql.Wrapper;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
* @author xingyijin
* @description 针对表【course】的数据库操作Service实现
* @createDate 2022-04-06 15:13:05
*/
@Service
@Transactional
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course>
    implements CourseService{

    @Autowired
    StudentService studentService;
    @Autowired
    CourseService courseService;
    @Autowired
    CourseTimeService courseTimeService;
    @Autowired
    SCourseClassRelationService sCourseClassRelationService;
    @Autowired
    SCourseService sCourseService;
    @Autowired
    ExerciseService exerciseService;
    @Autowired
    MaterialService materialService;

    @Override
    public List<Course> getAllCoursesByStuid(Integer stuid) {

//        List<Course> res = new ArrayList<>();
//        QueryWrapper<Student> studentQueryWrapper = new QueryWrapper<>();
//        studentQueryWrapper.eq("student_id", stuid);
//        Student student = studentMapper.selectOne(studentQueryWrapper);
//        if (student!= null){
////            QueryWrapper<TeacherCourseClassRelation> queryWrapper = new QueryWrapper<>();
////            queryWrapper.eq("class_id", student.getClassId());
////            List<TeacherCourseClassRelation> relations = teacherCourseClassRelationMapper.selectList(queryWrapper);
////            for (TeacherCourseClassRelation relation : relations) {
////                QueryWrapper<Course> wrapper = new QueryWrapper<>();
////                wrapper.eq("course_id", relation.getCourseId());
////                Course course = courseMapper.selectOne(wrapper);
////                res.add(course);
////            }
            return null;
//        }
//        return res;
    }

    @Override
    public Page<Course> getAllCourse(Integer page, Integer pageSize, String courseId, String courseName) {
        Page<Course> coursePage = new Page<>(page, pageSize);
        QueryWrapper<Course> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(!ObjectUtils.isEmpty(courseId), "course_id", courseId);
        queryWrapper.like(!ObjectUtils.isEmpty(courseName), "course_name", courseName);
        getBaseMapper().selectPage(coursePage, queryWrapper);
        return coursePage;
    }

    @Override
    public List<StuCourseVo> getCourses(Integer studentId, String courseName) {
        Student byId = studentService.getById(studentId);
        List<SCourseClassRelation> byClassId = sCourseClassRelationService.getByClassId(byId.getClassId());
        List<StuCourseVo> collect = byClassId.stream().map((r) -> {
            Course courseBySid = sCourseService.getCourseBySid(r.getSCourseId());
            StuCourseVo stuCourseVo = new StuCourseVo();
            stuCourseVo.setCourseId(courseBySid.getCourseId());
            stuCourseVo.setCourseName(courseBySid.getCourseName());
            stuCourseVo.setCourseImg(courseBySid.getCourseImg());
            stuCourseVo.setCourseDescription(courseBySid.getCourseDescription());
            stuCourseVo.setSCourseId(r.getSCourseId());
            return stuCourseVo;
        }).filter((course -> {
            if (courseName == null) {
                return true;
            }
            return course.getCourseName().contains(courseName);
        })).collect(Collectors.toList());
        return collect;
    }

    @Override
    public List<CourseTime> getCourseTime(Integer scid) {
        return courseTimeService.getBySCid(scid);
    }

    @Override
    public List<Exercise> getCourseExercise(Integer scid) {
        return exerciseService.getExerciseBySCid(scid);
    }

    @Override
    public List<Material> getCourseMaterial(Integer scid) {
        return materialService.getMaterialBySCid(scid);
    }

}




