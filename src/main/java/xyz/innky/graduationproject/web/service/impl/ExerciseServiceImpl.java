package xyz.innky.graduationproject.web.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import xyz.innky.graduationproject.common.utils.CopyUtil;
import xyz.innky.graduationproject.web.pojo.Exercise;
import xyz.innky.graduationproject.web.pojo.StudentExerciseRelation;
import xyz.innky.graduationproject.web.service.CourseService;
import xyz.innky.graduationproject.web.service.ExerciseService;
import xyz.innky.graduationproject.web.mapper.ExerciseMapper;
import org.springframework.stereotype.Service;
import xyz.innky.graduationproject.web.service.SCourseService;
import xyz.innky.graduationproject.web.service.StudentExerciseRelationService;
import xyz.innky.graduationproject.web.vo.CourseVo;
import xyz.innky.graduationproject.web.vo.ExerciseMarkVo;
import xyz.innky.graduationproject.web.vo.ExerciseVo;
import xyz.innky.graduationproject.web.vo.StudentExerciseVo;

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
    @Autowired
    SCourseService sCourseService;
    @Autowired
    StudentExerciseRelationService studentExerciseRelationService;

    @Override
    public List<Exercise> getExerciseBySCid(Integer scid) {
        return getBaseMapper().getAllBySCourseId(scid);
    }

    @Override
    public List<StudentExerciseVo> getStudentExercise(Integer studentId) {
        return getBaseMapper().getAllByStudentId(studentId);
    }

    @Override
    public List<ExerciseVo> getTeacherExercise(Integer teacherId) {
        List<CourseVo> courses = courseService.getTeacherCourses(teacherId);
        List<Exercise> allBySCourseIdIn = getBaseMapper().getAllBySCourseIdIn(courses.stream().map(CourseVo::getSCourseId).collect(Collectors.toList()));
        List<ExerciseVo> collect = allBySCourseIdIn.stream().map(e->{
            ExerciseVo exerciseVo = CopyUtil.copy(e, ExerciseVo.class);
            exerciseVo.setCourse(sCourseService.getCourseBySid( e.getSCourseId()));
            exerciseVo.setClassInfoList(sCourseService.getCourseClass(e.getSCourseId()));
            exerciseVo.setSubmittedCount(studentExerciseRelationService.getSubmittedCount(e.getExerciseId()));
            exerciseVo.setStudentCount(studentExerciseRelationService.getStudentCount(e.getExerciseId()));
            exerciseVo.setMarkedCount(studentExerciseRelationService.getMarkedCount(e.getExerciseId()));
            return exerciseVo;
        }).collect(Collectors.toList());

        return collect;
    }

    @Override
    public boolean saveExercise(Exercise exercise) {
        return getBaseMapper().insertSelective(exercise) > 0;
    }

    @Override
    public List<ExerciseMarkVo> getMark(Integer teacherId, Integer scid) {
        List<CourseVo> courses = courseService.getTeacherCourses(teacherId);
        List<Integer> exercises = getBaseMapper().getAllBySCourseIdIn(courses.stream().map(CourseVo::getSCourseId).filter(i->{
            if(scid == null){
                return true;
            }
            else {
                return i.equals(scid);
            }}).collect(Collectors.toList()))
                        .stream().map(Exercise::getExerciseId).collect(Collectors.toList());
        return studentExerciseRelationService.getMark(exercises);
//        List<ExerciseMarkVo> collect = allBySCourseIdIn.stream().map(e->{
    }

    @Override
    public boolean doMark(Integer id, Integer score) {
        return studentExerciseRelationService.doMark(id, score);
    }

    @Override
    public boolean submitExercise(StudentExerciseRelation content) {
        return studentExerciseRelationService.submitExercise(content);
    }

    @Override
    public StudentExerciseVo getByIdAndStudentId(Integer exerciseId, Integer studentId) {
        return studentExerciseRelationService.getByIdAndStudentId(exerciseId, studentId);
    }


}




