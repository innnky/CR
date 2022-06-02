package xyz.innky.graduationproject.web.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import xyz.innky.graduationproject.common.utils.CopyUtil;
import xyz.innky.graduationproject.common.utils.MyMailUtil;
import xyz.innky.graduationproject.web.pojo.Exercise;
import xyz.innky.graduationproject.web.pojo.StudentExerciseRelation;
import xyz.innky.graduationproject.web.pojo.UserAccount;
import xyz.innky.graduationproject.web.service.*;
import xyz.innky.graduationproject.web.mapper.ExerciseMapper;
import org.springframework.stereotype.Service;
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
    private MyMailUtil myMailUtil;
    @Autowired
    CourseService courseService;
    @Autowired
    SCourseService sCourseService;
    @Autowired
    StudentExerciseRelationService studentExerciseRelationService;
    @Autowired
    UserAccountService userAccountService;
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
    public List<ExerciseMarkVo> getMark(Integer teacherId, Integer exerciseId) {
        List<CourseVo> courses = courseService.getTeacherCourses(teacherId);
        return studentExerciseRelationService.getMark(exerciseId);
//        List<ExerciseMarkVo> collect = allBySCourseIdIn.stream().map(e->{
    }

    @Override
    public boolean doMark(Integer id, Integer score) {
        StudentExerciseRelation relation = studentExerciseRelationService.getById(id);
        UserAccount accountByStudentId = userAccountService.getAccountByStudentId(relation.getStudentId());

        if(accountByStudentId != null){
            myMailUtil.sendMail(accountByStudentId.getAccount(), "您的作业已被评分", "您的作业已被评分，评分为" + score + "分");
        }
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

    @Override
    public Boolean removeExercise(Integer id) {
        studentExerciseRelationService.removeByExerciseId(id);
        return removeById(id);
    }
}




