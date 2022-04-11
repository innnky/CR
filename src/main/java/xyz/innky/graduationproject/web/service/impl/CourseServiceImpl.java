package xyz.innky.graduationproject.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import xyz.innky.graduationproject.web.mapper.StudentMapper;
import xyz.innky.graduationproject.web.mapper.TeacherCourseClassRelationMapper;
import xyz.innky.graduationproject.web.pojo.Course;
import xyz.innky.graduationproject.web.pojo.Student;
import xyz.innky.graduationproject.web.pojo.TeacherCourseClassRelation;
import xyz.innky.graduationproject.web.service.CourseService;
import xyz.innky.graduationproject.web.mapper.CourseMapper;
import org.springframework.stereotype.Service;

import java.sql.Wrapper;
import java.util.ArrayList;
import java.util.List;

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
    TeacherCourseClassRelationMapper teacherCourseClassRelationMapper;
    @Autowired
    StudentMapper studentMapper;
    @Autowired
    CourseMapper courseMapper;
    @Override
    public List<Course> getAllCoursesByStuid(Integer stuid) {

        List<Course> res = new ArrayList<>();
        QueryWrapper<Student> studentQueryWrapper = new QueryWrapper<>();
        studentQueryWrapper.eq("student_id", stuid);
        Student student = studentMapper.selectOne(studentQueryWrapper);
        if (student!= null){
            QueryWrapper<TeacherCourseClassRelation> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("class_id", student.getClassId());
            List<TeacherCourseClassRelation> relations = teacherCourseClassRelationMapper.selectList(queryWrapper);
            for (TeacherCourseClassRelation relation : relations) {
                QueryWrapper<Course> wrapper = new QueryWrapper<>();
                wrapper.eq("course_id", relation.getCourseId());
                Course course = courseMapper.selectOne(wrapper);
                res.add(course);
            }
        }
        return res;
    }

    @Override
    public Page<Course> getAllCourse(Integer page, Integer pageSize, String courseId, String courseName) {
        Page<Course> coursePage = new Page<>(page, pageSize);
        QueryWrapper<Course> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(ObjectUtils.isEmpty(courseId), "course_id", courseId);
        queryWrapper.eq(ObjectUtils.isEmpty(courseName), "course_name", courseName);
        courseMapper.selectPage(coursePage, queryWrapper);
        return coursePage;
    }
}




